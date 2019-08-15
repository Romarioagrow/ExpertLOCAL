package expertshop.services;
import expertshop.domain.Product;
import expertshop.domain.User;
import expertshop.repos.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Log
@Service
@AllArgsConstructor
public class FilterService {
    private final ProductRepo productRepo;
    private final ProductService productService;

    public LinkedList<Object> resolveFilters(String productGroup)
    {
        Set<String> brands = new TreeSet<>();
        //Set<String> filtersRUS = new TreeSet<>();
        Map<String, TreeSet<String>> filters = new TreeMap<>();

        try
        {
            List<Product> products = productRepo.findProductsByProductGroupEqualsIgnoreCaseAndIsAvailableTrue(productGroup);
            products.forEach(product ->
            {
                /// Вывести все бренды
                brands.add(StringUtils.capitalize(product.getOriginalBrand().toLowerCase()));

                /// Вывести все неповторяющиеся RBT фильтры из аннотации
                if (product.getSupplier().startsWith("1RBT"))
                {
                    String[] filtrs = product.getOriginalAnnotation().split(";");
                    for (String fltr : filtrs)
                    {
                        String key = StringUtils.substringBefore(fltr, ":").trim();
                        String val = StringUtils.substringAfter(fltr, ":").trim();
                        if (!key.isEmpty() && !val.startsWith("-") && !val.startsWith("нет") && !val.startsWith("0") && !key.startsWith("количество шт в") && !key.contains("количество шт в кор"))
                        {
                            if (filters.get(key) != null)
                            {
                                TreeSet<String> vals = filters.get(key);
                                vals.add(val);
                                filters.put(key, vals);
                            }
                            else filters.putIfAbsent(key, new TreeSet<>(Collections.singleton(val)));
                        }
                    }
                }
            });

            /// Filters RUS
            if (brands.size() == 0)
            {
                products = productRepo.findByOriginalTypeIgnoreCaseAndIsAvailableIsTrue(productGroup);
                products.forEach(product -> {
                    if (!product.getOriginalBrand().isEmpty()) {
                        brands.add(StringUtils.capitalize(product.getOriginalBrand().toLowerCase()));
                    }
                });
                products.forEach(product ->{
                    String annotation = StringUtils.substringAfter(product.getOriginalName(), ",");
                    String[] items = annotation.split(", ");

                    for (String item : items) {
                        if (StringUtils.countMatches(StringUtils.capitalize(item.trim().toLowerCase()), " ") == 1)
                        {
                            String key = StringUtils.substringAfter(item.trim(), " ");
                            String val = StringUtils.substringBeforeLast(item.trim(), " ").replaceAll(" ", "");
                            String filter = val.concat(" ").concat(key);

                            /*!!!ПРОВЕРКА REGEX НА ТОЛЬКО ЦИФРЫ, ЕСЛИ FALSE ТО В ОСОБЕННОСТИ!!!*/
                            /*!!!ПРОВЕРКА REGEX НА ВЕЛИЧИНЫ БЕЗ ПРОБЕЛОВ(ММ, см), ЕСЛИ true ТО разделить пробелом!!!*/

                            if (!item.contains("арт.")) {
                                if (filters.get(key) != null)
                                {
                                    TreeSet<String> vals = filters.get(key);
                                    vals.add(filter);
                                    filters.put(key, vals);
                                }
                                else filters.putIfAbsent(key, new TreeSet<>(Collections.singleton(filter)));
                            }
                        }
                        else
                        {
                            if (filters.get("Особенности") != null && !item.isEmpty())
                            {
                                TreeSet<String> vals = filters.get("Особенности");
                                vals.add(item.trim());
                                filters.put("Особенности", vals);
                            }
                            else filters.putIfAbsent("Особенности", new TreeSet<>(Collections.singleton(item.trim())));
                        }
                    }
                });
            }
        }
        catch (NullPointerException e) {
            log.warning(e.getClass().getName());
            e.printStackTrace();
        }

        LinkedList<Object> payload = new LinkedList<>();
        payload.add(brands);
        if (filters.size() != 0) payload.add(filters);
        return payload;
    }

    public LinkedList<Object> filterProducts(Map<String, String> filters, String request, Pageable pageable, User user)
    {
        filters.forEach((key, filter) -> log.info(key + " " + filter));

        List<Product> products = productRepo.findProductsByProductGroupEqualsIgnoreCaseAndIsDuplicateIsNullAndIsAvailableTrue(request);

        if (products.size() == 0) {
            products = productRepo.findByOriginalTypeIgnoreCaseAndIsAvailableIsTrue(request);
            log.info("Product list before filter: " + products.size());
        }

        for (Map.Entry<String, String> filter : filters.entrySet())
        {
            try
            {
                String condition = filter.getKey();
                if (condition.startsWith("Cont-")) {
                    products = filterContainsParams(products, filter);
                }
                else if (condition.startsWith("Comp-")) {
                    products = filterComputeParams (products, filter);
                }
                else switch (condition) {
                        case "sortmin", "sortmax"   -> products = filterPrice   (products, filter, request);
                        case "brand"                -> products = filterBrands  (products, filter);
                    }
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        if (products != null)
        {
            log.info("Product after filter " + products.size());
            sortProducts(products, filters);

            int start = (int) pageable.getOffset();
            int end = (start + pageable.getPageSize()) > products.size() ? products.size() : (start + pageable.getPageSize());

            Page page = new PageImpl<>(products.subList(start, end), pageable, products.size());
            Set<String> orderedIDs = productService.getOrderedID(user);

            LinkedList<Object> payload = new LinkedList<>();
            payload.add(page);
            payload.add(orderedIDs);
            return payload; /// payload();
        }
        return null;
    }

    private List<Product> filterContainsParams(List<Product> products, Map.Entry<String, String> filter) {
        String[] params = filter.getValue().replaceAll(";","").split(", "/*resolveSplitter(filter.getKey())*/);
        log.info(Arrays.toString(params));
        if (filter.getKey().contains("MultiParams"))
        {
            return products.stream().filter(product ->
            {
                if (product.getSupplier().equals("1RBT")) ///checkSupp()
                {
                    AtomicInteger matches = new AtomicInteger(0);
                    for (String val : params)
                    {
                        String param = val.replaceAll("[\\[\\]]", "").trim();
                        if (product.getOriginalAnnotation().contains(param) || product.getProductType().contains(param)) {
                            matches.getAndIncrement();
                        }
                    }
                    return matches.get() == params.length;
                }
                else return false;

            }).collect(Collectors.toList());
        }
        /// В ОТДЕЛЬНЫЙ МЕТОД В CASE
        else return products.stream().filter(product ->
        {
            if (product.getSupplier().equals("1RBT") && product.getOriginalAnnotation() != null && product.getProductType() != null) ///checkSupp()
            {
                for (String val : params)
                {
                    String param = val.replaceAll("[\\[\\]]", "").trim();
                    if (product.getOriginalAnnotation().contains(param) || product.getProductType().contains(param)) {
                        return true;
                    }
                }
                return false;
            }
            else
            {
                //AtomicInteger matches = new AtomicInteger(0);
                for (String item : params)
                {
                    //log.info(item);
                    String param = StringUtils.substringAfter(item, ": ");
                    log.info(params.length + "");
                    log.info("param "+param);
                    if (StringUtils.containsIgnoreCase(product.getOriginalName(), param)) {
                        //matches.getAndIncrement();
                        return true;
                    }
                }
                return false;
            }
        }).collect(Collectors.toList());
    }

    private List<Product> filterComputeParams(List<Product> products, Map.Entry<String, String> filter) {
        String filterKey = StringUtils.substringBefore(filter.getKey(), ";");

        double checkMin      = Double.parseDouble(StringUtils.substringBetween(filter.getKey(), ";","/"));
        double checkMax      = Double.parseDouble(StringUtils.substringAfter(filter.getKey(), "/"));
        double computeFilter = Double.parseDouble(filter.getValue().replaceAll(",","."));

        if (computeFilter >= checkMin && computeFilter <= checkMax)
        {
            return products.stream().filter(product ->
            {
                if (product.getOriginalAnnotation() != null && !product.getOriginalAnnotation().isEmpty())
                {
                    double computeParam  = extractComputeParam(product, filterKey);

                    if (computeParam == 0) return false;
                    return filterKey.contains("Min") ? computeFilter <= computeParam : computeFilter >= computeParam;
                }
                return false;
            }).collect(Collectors.toList());
        }
        return null;
    }
    private double extractComputeParam(Product product, String filterKey) {
        String annotation = product.getOriginalAnnotation();
        String keyName    = StringUtils.substringAfterLast(filterKey, "-");

        /// Проверка закрытия аннотации ;
        if (!annotation.endsWith(";")) {
            annotation = annotation.concat(";");
        }
        /*log.info(annotation);
        log.info(keyName);*/
        if (annotation.contains(keyName))
        {
            String compVal = StringUtils.substringBetween(annotation, keyName, ";").replaceAll(",", ".");
            compVal = compVal.replaceAll("[^\\d.]", "");
            log.info(compVal);

            return !compVal.contains("-") ? Double.parseDouble(compVal) : 0;
        }
        return 0;
    }

    private List<Product> filterPrice(List<Product> products, Map.Entry<String, String> filter, String request) {
        log.info("req " +request);
        int min = productService.getMinMaxPrice(request)[0];
        int max = productService.getMinMaxPrice(request)[1];
        int filterVal = Integer.parseInt(filter.getValue());


        if (filterVal >= min && filterVal <= max)
        {
            try {
                return filter.getKey().equals("sortmin") ?
                        products.stream().filter(product -> product.getFinalPrice() >= filterVal).collect(Collectors.toList()):
                        products.stream().filter(product -> product.getFinalPrice() <= filterVal).collect(Collectors.toList());
            }
            catch (NullPointerException ignored) {
            }
        }
        return null;
    }

    private List<Product> filterBrands(List<Product> products, Map.Entry<String, String> filter) {
        String brands = String.join(",", filter.getValue());
        return products.stream().filter(product -> StringUtils.containsIgnoreCase(brands, product.getOriginalBrand())).collect(Collectors.toList());
    }

    private void sortProducts(List<Product> products, Map<String, String> params)
    {
        try {


            String sort = params.get("sortOrder");
            switch (sort) {
                case "lowest"   -> products.sort(Comparator.comparingLong(Product::getFinalPrice));
                case "highest"  -> products.sort(Comparator.comparingLong(Product::getFinalPrice).reversed());
                case "alphabet" -> products.sort(Comparator.comparing(Product::getOriginalBrand));
                default         -> products.sort(Comparator.comparing(Product::getLocalPic));
            }
        }
        catch (NullPointerException e){
            e.getClass().getName();
        }
    }
}



    /*
    private Queue<String> packageProductsAndOrderedID(List<Product> products, User user) {
        Queue<String> productsAndOrder = new LinkedList<>();
        productsAndOrder.add(products);
        productsAndOrder.add(getOrderedID(user));
        return productsAndOrder;
    }

    public Set<String> getOrderedID(User user)
    {
        if (user != null && orderRepo.findByUserIDAndAcceptedFalse(user.getUserID()) != null)
        {
            Order order = orderRepo.findByUserIDAndAcceptedFalse(user.getUserID());
            return collectID(order);
        }
        else if (orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID()) != null)
        {
            Order order = orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID());
            return collectID(order);
        }
        else return new HashSet<>();
    }

    Set<String> collectID(Order order) {
        Set<String> orderedProductsID = new HashSet<>();

        for (OrderedProduct product : order.getOrderedProducts())
            orderedProductsID.add(product.getProductID().toString());

        return orderedProductsID;
    }

    private String extractParamValue(Map<String, String> params, String primaryParam, String innerParam) {
        Map<String, String> paramType = (Map<String, String>) params.get(primaryParam);
        return paramType.get(innerParam);
    }

    private void showReceivedParams (Map<String, String> params) {
        log.info("\nServer received params with args:");
        params.forEach((param, args) -> log.info(param + ":" + Arrays.toString(new Map[]{(Map) args})));
    }*/


