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

        List<Product> products = productRepo.findByType(Type.valueOf(req_type));

        return filter(params, products);
    }

    private List<Product> filter(Map<String, Object> params, List<Product> products)
    {
        for (Map.Entry<String, Object> paramObject : params.entrySet())
        {
            Map<String, Object> inner = (Map<String, Object>) paramObject.getValue();
            for (Map.Entry<String, Object> entry : inner.entrySet())
            {
                switch (entry.getKey()) {
                    case "sortmin"      -> products = products.stream().filter(product -> product.getPrice() >= Integer.parseInt((String) entry.getValue())).collect(Collectors.toList());
                    case "sortmax"      -> products = products.stream().filter(product -> product.getPrice() <= Integer.parseInt((String) entry.getValue())).collect(Collectors.toList());
                    case "brand"        -> products = products.stream().filter(product -> product.getBrand().equals(entry.getValue())).collect(Collectors.toList());
                    case "country"      -> products = products.stream().filter(product -> product.getCountry().equals(entry.getValue())).collect(Collectors.toList());
                    case "diag_min"     -> products = products.stream().filter(product -> Integer.parseInt(product.getDiagonal()) >= Integer.parseInt(entry.getValue().toString())).collect(Collectors.toList());
                    case "diag_max"     -> products = products.stream().filter(product -> Integer.parseInt(product.getDiagonal()) <= Integer.parseInt(entry.getValue().toString())).collect(Collectors.toList());
                    case "tv_resolution" -> {
                        String resolution = entry.getValue().toString();
                        products = products.stream().filter(product -> resolution.contains(product.getResolution())).collect(Collectors.toList());
                    }
                    case "tv_params" -> {
                        List<Object> elements = new ArrayList<>((Collection<?>) entry.getValue());
                        for (Object in : elements) {
                            products.forEach(product -> log.info(String.valueOf(product.getTvFeatures().contains(in.toString()))));
                            products = products.stream().filter(product -> product.getTvFeatures().contains(in.toString())).collect(Collectors.toList());
                        }
                    }
                }
            }
        }
        log.info("After filter: " + products.size());
        return products;
    }

    private Object extractParamValue(Map<String, Object> params, String primaryParam, String innerParam) {
        ///ВКЛЮЧИТЬ ПРОВЕРКУ НА NULL ДЛЯ ОБОИХ STRING
        Map<String, Object> paramType = (Map<String, Object>) params.get(primaryParam);
        return paramType.get(innerParam);
    }

    private void showReceivedParams (Map<String, Object> params) {
        log.info("\nServer received params with args:");
        params.forEach((param, args) -> log.info(param + ":" + Arrays.toString(new Map[]{(Map) args})));
    }
}

