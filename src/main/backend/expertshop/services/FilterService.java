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
        Map<String, TreeSet<String>> filters = new TreeMap<>();

        try
        {
            List<Product> products = productRepo.findProductsByProductGroupEqualsIgnoreCase(productGroup);
            products.forEach(product ->
            {
                brands.add(StringUtils.capitalize(product.getOriginalBrand().toLowerCase()));

                if (product.getSupplier().equals("1RBT"))
                {
                    String[] filtrs = product.getOriginalAnnotation().split(";");
                    for (String fltr : filtrs)
                    {
                        String key = StringUtils.substringBefore(fltr, ":").trim();
                        String val = StringUtils.substringAfter(fltr, ":").trim();
                        if (!key.isEmpty() && !val.startsWith("-") && !val.startsWith("нет") && !val.startsWith("0") && !key.startsWith("количество шт в"))
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
            /*products.forEach(product -> {
                if (product.getSupplier().startsWith("2")) {
                    String[] filtrs = product.getOriginalAnnotation().split(";");
                    for (String fltr : filtrs) {
                        for (Map.Entry<String, TreeSet<String>> entry : filters.entrySet()) {
                            if (StringUtils.containsIgnoreCase(entry.getKey().replaceAll(" ", ""), fltr.replaceAll(" ", ""))) {
                                System.out.println();
                                //log.info("RBT KEY " + entry.getKey());
                                //log.info("RUS PARAM " + fltr);
                            }
                        }
                    }
                }
            });*/
            /*System.out.println();
            filters.forEach((s, strings) -> log.info(s + " " + strings.toString()));*/
        }
        catch (NullPointerException e) {
            log.warning(e.getClass().getName());//e.printStackTrace();
        }

        LinkedList<Object> payload = new LinkedList<>();
        payload.add(brands);
        payload.add(filters);
        return payload;
    }

    public LinkedList<Object> filterProducts(Map<String, String> filters, String request, Pageable pageable, User user)
    {
        filters.forEach((key, filter) -> log.info(key + " " + filter));

        List<Product> products = productRepo.findProductsByProductGroupEqualsIgnoreCase(request);
        log.info("Product list before filter: " + products.size());

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
        String[] params = filter.getValue().split(resolveSplitter(filter.getKey()));
        log.info(Arrays.toString(params));

        //paramRus
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
        /// В ОТДЕЛЬНЫЙ МЕТОД
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
            else return false;
        }).collect(Collectors.toList());
    }
    private String resolveSplitter(String key) {
        return switch (key) {
            case "Cont-tvCablesLength", "Cont-TeapotMaterial" -> ";,";
            default -> ",";
        };
    }

    private List<Product> filterComputeParams(List<Product> products, Map.Entry<String, String> filter) {
        String filterKey = StringUtils.substringBefore(filter.getKey(), ";");

        double checkMin      = Double.parseDouble(StringUtils.substringBetween(filter.getKey(), ";","/"));
        double checkMax      = Double.parseDouble(StringUtils.substringAfter(filter.getKey(), "/"));
        double computeFilter = Double.parseDouble(filter.getValue());

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

        if (annotation.contains(keyName))
        {
            String compVal = StringUtils.substringBetween(annotation, keyName.concat(": "), ";").replaceAll(",", ".");
            return !compVal.contains("-") ? Double.parseDouble(compVal) : 0;
        }
        return 0;
    }

    private List<Product> filterPrice(List<Product> products, Map.Entry<String, String> filter, String request) {
        int min = productService.getMinMaxPrice(request)[0];
        int max = productService.getMinMaxPrice(request)[1];
        int filterVal = Integer.parseInt(filter.getValue());

        if (filterVal >= min && filterVal <= max)
        {
            return filter.getKey().equals("sortmin") ?
                    products.stream().filter(product -> product.getFinalPrice() >= filterVal).collect(Collectors.toList()):
                    products.stream().filter(product -> product.getFinalPrice() <= filterVal).collect(Collectors.toList());
        }
        return null;
    }

    private List<Product> filterBrands(List<Product> products, Map.Entry<String, String> filter) {
        String brands = String.join(",", filter.getValue());
        return products.stream().filter(product -> StringUtils.containsIgnoreCase(brands, product.getOriginalBrand())).collect(Collectors.toList());
    }

    private void sortProducts(List<Product> products, Map<String, String> params)
    {
        String sort = params.get("sortOrder");
        switch (sort) {
            case "lowest"   -> products.sort(Comparator.comparingLong(Product::getFinalPrice));
            case "highest"  -> products.sort(Comparator.comparingLong(Product::getFinalPrice).reversed());
            case "alphabet" -> products.sort(Comparator.comparing(Product::getOriginalBrand));
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


