package expertshop.services;
import expertshop.domain.Product;
import expertshop.repos.ProductRepo;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

        filters.forEach((key, filter) -> log.info(key + " " + filter.toString()));

        List<Product> products = productRepo.findProductsByProductGroupEqualsIgnoreCase(requiredType);
        log.info("Product list before filter " + products.size());

        for (Map.Entry<String, Object> filter : filters.entrySet())
        {
            try
            {
                switch (filter.getKey())
                {
                    case "sortmin", "sortmax"   -> products = filterPrice(products, filter);
                    case "brand"                -> products = filterBrands(products, filter);
                    case "diagMin", "diagMax"   -> products = filterTvDiag(products, filter);
                    case "tvResolution"         -> products = filterTvResol(products, filter);
                    case "hzMin", "hzMax"       -> products = filterTvHZ(products, filter);
                    case "tvParams", "tvType", "tvCables", "tvCablesType", "tvCablesLength" -> products = filterParamsInAnnotationAndType(products, filter);
                }
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        log.info("Product after filter " + products.size());

        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > products.size() ? products.size() : (start + pageable.getPageSize());
        return new PageImpl<>(products.subList(start, end), pageable, products.size());
    }

    private List<Product> filterParamsInAnnotationAndType(List<Product> products, Map.Entry<String, Object> filter)
    {
        String[] tvParams = filter.getValue().toString().split(resolveSplitter(filter.getKey()));
        log.info(Arrays.toString(tvParams));

        return products.stream().filter(product ->
        {
            for (String val : tvParams)
            {
                String param = val.replaceAll("[\\[\\]]", "").trim();

                System.out.println();
                log.info("Param: " + param);
                log.info("Product Anno: " + product.getOriginalAnnotation());

                if (product.getOriginalAnnotation().contains(param) || product.getProductType().contains(param))
                {
                    log.info(param + " | " + product.getOriginalAnnotation());
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
    }

    private String resolveSplitter(String key) {
        return switch (key) {
            case "tvCablesLength" -> ";,";
            default -> ",";
        };
    }

    private List<Product> filterPrice(List<Product> products, Map.Entry<String, Object> filter) {
        return filter.getKey().equals("sortmin") ?
                products.stream().filter(product -> product.getPrice() >= Integer.parseInt(filter.getValue().toString())).collect(Collectors.toList()):
                products.stream().filter(product -> product.getPrice() <= Integer.parseInt(filter.getValue().toString())).collect(Collectors.toList());
    }

    private List<Product> filterBrands(List<Product> products, Map.Entry<String, Object> filter) {
        String brands = String.join(",", filter.getValue().toString());
        return products.stream().filter(product -> StringUtils.containsIgnoreCase(brands, product.getOriginalBrand())).collect(Collectors.toList());
    }

    private List<Product> filterTvHZ(List<Product> products, Map.Entry<String, Object> filter) {
        int hz = Integer.parseInt(filter.getValue().toString());
        return products.stream().filter(product ->
        {
            if (product.getOriginalAnnotation().contains("Индекс частоты обновления:")) ///checkParam()
            {
                int productHZ = Integer.parseInt(StringUtils.substringBetween(product.getOriginalAnnotation(), "Индекс частоты обновления:", ";").trim());
                return filter.getKey().equals("hzMin") ? hz <= productHZ : hz >= productHZ;
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Product> filterTvResol(List<Product> products, Map.Entry<String, Object> filter) {
        String resolution = filter.getValue().toString();
        return products.stream().filter(product ->
        {
            if (product.getOriginalAnnotation().contains("Разрешение:"))
            {
                String productResol = StringUtils.substringBetween(product.getOriginalAnnotation(), "Разрешение", ";").trim();
                productResol = StringUtils.substringBetween(productResol, "(", ")");
                return resolution.contains(productResol);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Product> filterTvDiag(List<Product> products, Map.Entry<String, Object> filter) {
        return products.stream().filter(product ->
        {
            if (product.getOriginalAnnotation().contains("Диагональ"))
            {
                String diag = StringUtils.substringBetween(product.getOriginalAnnotation(), "Диагональ:", ";").trim();
                if (diag.contains(",")) diag = diag.replaceAll(",", ".");
                double productDiag = Double.parseDouble(diag);
                return filter.getKey().equals("diagMin") ? Double.parseDouble(filter.getValue().toString()) <= productDiag : Double.parseDouble(filter.getValue().toString()) >= productDiag;
            }
            return false;
        }).collect(Collectors.toList());
    }
}




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


