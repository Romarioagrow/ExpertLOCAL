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
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log
@Service
@AllArgsConstructor
public class FilterService {
    private final ProductRepo productRepo;

    public Page<Product> filterProducts(Map<String, Object> filters, String requiredType, Pageable pageable)
    {
        log.info(requiredType);

        filters.forEach((s, o) -> log.info(s + " " + o.toString()));

        List<Product> products = productRepo.findProductsByProductGroupEqualsIgnoreCase(requiredType);
        log.info("Product list before filter " + products.size());

        /*коллкеция всех товаров по типу и из нее наполняется коллекция запрашеваемых товаров по фильтру?*/
        /*ОТДЕЛЬНЫЙ ФИЛЬТР ПО ЦЕНЕ?*/

        for (Map.Entry<String, Object> filter : filters.entrySet())
        {
            /*ложить productGroup в объект фильтров и разделять по productGroup*/
            switch (filter.getKey())
            {
                case "sortmin" -> products = products.stream().filter(product -> product.getPrice() >= Integer.parseInt(filter.getValue().toString())).collect(Collectors.toList());
                case "sortmax" -> products = products.stream().filter(product -> product.getPrice() <= Integer.parseInt(filter.getValue().toString())).collect(Collectors.toList());
                case "brand" -> {
                    String brands = String.join(",", filters.get("brand").toString());
                    log.info(brands);
                    products = products.stream().filter(product -> StringUtils.containsIgnoreCase(brands, product.getOriginalBrand())).collect(Collectors.toList());
                }
                /*case "tvResolution" -> {
                    String resolution = filter.getValue().toString();
                    log.info(resolution);
                    products = products.stream().filter(product -> resolution.contains(Objects.requireNonNull(product.get_tv_resol()))).collect(Collectors.toList());
                }*/
            }
        }

        products.forEach(product -> log.info(product.getModelName()));

        log.info("Product after filter " + products.size());

        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > products.size() ? products.size() : (start + pageable.getPageSize());
        Page<Product> page = new PageImpl<>(products.subList(start, end), pageable, products.size());

        log.info(page.getTotalElements() + " ");
        return page;
    }
}
       /* showReceivedParams(params);

        List<Product> products = productRepo.findByType(req_type);

        for (Map.Entry<String, Object> paramObject : params.entrySet())
        {
            Map<String, Object> inner = (Map<String, Object>) paramObject.getValue();
            for (Map.Entry<String, Object> filter : inner.entrySet())
            {
                switch (filter.getKey()) {
                    case "sortmin"      -> products = products.stream().filter(product -> product.getFinalPrice() >= Integer.parseInt(filter.getValue().toString())).collect(Collectors.toList());
                    case "sortmax"      -> products = products.stream().filter(product -> product.getFinalPrice() <= Integer.parseInt(filter.getValue().toString())).collect(Collectors.toList());
                    case "brand"        -> {
                        String brands = filter.getValue().toString();
                        products = products.stream().filter(product -> brands.contains(product.getBrand())).collect(Collectors.toList());
                    }
                    case "country"      -> {
                        String countries = filter.getValue().toString();
                        products = products.stream().filter(product -> countries.contains(product.getCountry())).collect(Collectors.toList());
                    }
                    case "diag_min"     -> products = products.stream().filter(product -> Integer.parseInt(product.getDiagonal()) >= Integer.parseInt(filter.getValue().toString())).collect(Collectors.toList());
                    case "diag_max"     -> products = products.stream().filter(product -> Integer.parseInt(product.getDiagonal()) <= Integer.parseInt(filter.getValue().toString())).collect(Collectors.toList());
                    case "tv_resolution" -> {
                        String resolution = filter.getValue().toString();
                        products = products.stream().filter(product -> resolution.contains(product.getResolution())).collect(Collectors.toList());
                    }
                    case "tv_params" -> {
                        List<Object> tv_params = new ArrayList<>((Collection<?>) filter.getValue());
                        for (Object tv_param : tv_params) {
                            products = products.stream().filter(product -> product.getTvFeatures().contains(tv_param.toString())).collect(Collectors.toList());
                        }
                    }
                }
            }
        }
        sortProducts(products, params);
        log.info("After filter: " + products.size());

        return packageProductsAndOrderedID(products, user);
        //return new LinkedList<>();
    }*/

    /*private void sortProducts(List<Product> products, Map<String, Object> params)
    {
        Object sort = extractParamValue(params, "sortBy", "sortOrder");
        switch (sort.toString()) {
            *//*case "lowest"   -> products.sort(Comparator.comparingLong(Product::getFinalPrice));
            case "highest"  -> products.sort(Comparator.comparingLong(Product::getFinalPrice).reversed());
            case "alphabet" -> products.sort(Comparator.comparing(Product::getBrand));*//*
        }
    }

    private Queue<Object> packageProductsAndOrderedID(List<Product> products, User user) {
        Queue<Object> productsAndOrder = new LinkedList<>();
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

    private Object extractParamValue(Map<String, Object> params, String primaryParam, String innerParam) {
        Map<String, Object> paramType = (Map<String, Object>) params.get(primaryParam);
        return paramType.get(innerParam);
    }

    private void showReceivedParams (Map<String, Object> params) {
        log.info("\nServer received params with args:");
        params.forEach((param, args) -> log.info(param + ":" + Arrays.toString(new Map[]{(Map) args})));
    }*/


