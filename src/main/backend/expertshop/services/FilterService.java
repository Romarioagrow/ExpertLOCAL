package expertshop.services;

import expertshop.domain.Product;
import expertshop.domain.categories.Type;
import expertshop.repos.ProductRepo;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Log
@Service
@AllArgsConstructor
public class FilterService {
    private final ProductRepo productRepo;

    public List<Product> filterProducts(Map<String, Object> params, String req_type) {
        showReceivedParams(params);
        return filter(params, productRepo.findByType(Type.valueOf(req_type)));
    }

    private List<Product> filter(Map<String, Object> params, List<Product> products)
    {
        for (Map.Entry<String, Object> paramObject : params.entrySet())
        {
            Map<String, Object> inner = (Map<String, Object>) paramObject.getValue();
            for (Map.Entry<String, Object> filter : inner.entrySet())
            {
                switch (filter.getKey()) {
                    case "sortmin"      -> products = products.stream().filter(product -> product.getPrice() >= Integer.parseInt(/*(String)*/ filter.getValue().toString())).collect(Collectors.toList());
                    case "sortmax"      -> products = products.stream().filter(product -> product.getPrice() <= Integer.parseInt(/*(String)*/ filter.getValue().toString())).collect(Collectors.toList());
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

        return products;
    }

    private void sortProducts(List<Product> products, Map<String, Object> params)
    {
        Object sort = extractParamValue(params, "sortBy", "sortOrder");
        switch (sort.toString()) {
            case "lowest"   -> products.sort(Comparator.comparingLong(Product::getPrice));
            case "highest"  -> products.sort(Comparator.comparingLong(Product::getPrice).reversed());
            case "alphabet" -> products.sort(Comparator.comparing(Product::getBrand));
        }
    }

    private Object extractParamValue(Map<String, Object> params, String primaryParam, String innerParam) {
        Map<String, Object> paramType = (Map<String, Object>) params.get(primaryParam);
        return paramType.get(innerParam);
    }

    private void showReceivedParams (Map<String, Object> params) {
        log.info("\nServer received params with args:");
        params.forEach((param, args) -> log.info(param + ":" + Arrays.toString(new Map[]{(Map) args})));
    }
}

