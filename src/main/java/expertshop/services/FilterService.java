package expertshop.services;
import expertshop.domain.Product;
import expertshop.domain.categories.Type;
import expertshop.repos.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
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

    public List<Product> filterProducts(Map<String, String[]> params) {
        showReceivedParams(params);

        /*String requiredType = params.requiredType*/
        List<Product> products = productRepo.findByType(Type.tv);

        /*if (productIsTV)*/
        products = filterByPrice        (products, params);
        products = filterByManufacturer (products, params);

        products = filterByDiagonal     (products, params);
        products = filterByResolution   (products, params);
        products = filterByTvParams     (products, params);

        log.info("After filter: " + products.size());
        return products;
    }

    private List<Product> filterByPrice(List<Product> products, Map<String, String[]> params) {
        if (params.get("sortmin") != null )
        {
            String[] sortmin = params.get("sortmin");
            products = products.stream()
                    .filter(product -> product.getPrice() >= Integer.parseInt(sortmin[0]))
                    .collect(Collectors.toList());
        }
        if (params.get("sortmax") != null)
        {
            String[] sortmax = params.get("sortmax");
            products = products.stream()
                    .filter(product -> product.getPrice() <= Integer.parseInt(sortmax[0]))
                    .collect(Collectors.toList());
        }
        return products;
    }

    private List<Product> filterByManufacturer(List<Product> products, Map<String, String[]> params) {
        if (params.get("brands") != null)
        {
            String[] brand = params.get("brands");
            products = products.stream()
                    .filter(product -> product.getBrand().equals(brand[0]))
                    .collect(Collectors.toList());
        }
        if (params.get("country") != null)
        {
            String[] country = params.get("country");
            products = products.stream()
                    .filter(product -> product.getCountry().equals(country[0]))
                    .collect(Collectors.toList());
        }
        return products;
    }

    private List<Product> filterByDiagonal(List<Product> products, Map<String, String[]> params) {
        if (params.get("diag_min") != null)
        {
            String[] diag_min = params.get("diag_min");
            products = products.stream()
                    .filter(product -> Integer.parseInt(product.getDiagonal()) >= Integer.parseInt(diag_min[0]))
                    .collect(Collectors.toList());
        }

        if (params.get("diag_max") != null) {
            String[] diag_max = params.get("diag_max");
            products = products.stream()
                    .filter(product -> Integer.parseInt(product.getDiagonal()) <= Integer.parseInt(diag_max[0]))
                    .collect(Collectors.toList());
        }

        return products;
    }

    private List<Product> filterByResolution(List<Product> products, Map<String, String[]> params) {
        if (params.get("resolution") != null)
        {
            String[] resolution = params.get("resolution");
            log.info("Resol. filters: " + resolution.length);
            log.info("Checked: " + Arrays.toString(resolution));

            if (resolution.length == 3) {
                products = products.stream()
                        .filter(product -> (product.getResolution().contains(resolution[0]))
                                | (product.getResolution().contains(resolution[1]))
                                | (product.getResolution().contains(resolution[2])))
                        .collect(Collectors.toList());
            }
            if (resolution.length == 2) {
                products = products.stream()
                        .filter(product -> (product.getResolution().contains(resolution[0]))
                                | (product.getResolution().contains(resolution[1])))
                        .collect(Collectors.toList());
            }
            if (resolution.length == 1) {
                products = products.stream()
                        .filter(product -> (product.getResolution().contains(resolution[0])))
                        .collect(Collectors.toList());
            }
        }
        return products;
    }

    private List<Product> filterByTvParams(List<Product> products, Map<String, String[]> params) {
        if (params.get("params") != null)
        {
            String[] tv_params  = params.get("params");
            log.info("TvFeatures filters: " + tv_params.length);
            log.info("Checked: " + Arrays.toString(tv_params));

            if (tv_params.length == 4)
            {
                products = products.stream()
                        .filter(product -> ((product.getTvFeatures() != null)
                                && (product.getTvFeatures().contains(tv_params[0]))
                                | (product.getTvFeatures().contains(tv_params[1]))
                                | (product.getTvFeatures().contains(tv_params[2]))
                                | (product.getTvFeatures().contains(tv_params[3]))))
                        .collect(Collectors.toList());
            }
            if (tv_params.length == 3)
            {
                products = products.stream()
                        .filter(product -> ((product.getTvFeatures() != null)
                                && (product.getTvFeatures().contains(tv_params[0]))
                                | (product.getTvFeatures().contains(tv_params[1]))
                                | (product.getTvFeatures().contains(tv_params[2]))))
                        .collect(Collectors.toList());
            }
            if (tv_params.length == 2)
            {
                products = products.stream()
                        .filter(product -> ((product.getTvFeatures() != null)
                                && (product.getTvFeatures().contains(tv_params[0]))
                                | (product.getTvFeatures().contains(tv_params[1]))))
                        .collect(Collectors.toList());
            }
            if (tv_params.length == 1)
            {
                products = products.stream()
                        .filter(product -> ((product.getTvFeatures() != null)
                                && product.getTvFeatures().contains(tv_params[0])))
                        .collect(Collectors.toList());
            }
        }
        return products;
    }

    private void showReceivedParams(Map<String, String[]> params) {
        log.info("\nServer received params with args:");
        params.forEach((param, args) -> log.info(param + ":" + Arrays.toString(args)));
    }
}


/*
Optional<String> res1 = Optional.ofNullable(resolution[0]);
Optional<String> res2 = Optional.ofNullable(resolution[1]);
Optional<String> res3 = Optional.ofNullable(resolution[2]);
*/

/*List<Product> filterByDiagonal(List<Product> products, Map<String, String[]> params) {
        String[] diag_min   = params.get("diag_min");
        String[] diag_max   = params.get("diag_max");
        //if (!diag_min[0].isEmpty()) {
        Optional.ofNullable(diag_min[0]).filter();
                .map(String::toLowerCase);
     Optional.ofNullable(diag_min[0]).ifPresent(s -> );
     Optional.ofNullable(diag_min[0]).ifPresent(products = products.stream()
                .filter(product -> Integer.parseInt(product.getDiagonal()) >= Integer.parseInt(diag_min[0])));
        products = products.stream()
                    .filter(Optional.ofNullable(diag_min[0]).ifPresent(product -> Integer.parseInt(product.get()) >= Integer.parseInt(diag_min[0])))
                    .filter(product -> !diag_max[0].isEmpty() && Integer.parseInt(product.getDiagonal()) <= Integer.parseInt(diag_max[0]))
                    .collect(Collectors.toList());
        }
        if (!diag_max[0].isEmpty()) {
            products = products.stream()
                    .filter(product -> Integer.parseInt(product.getDiagonal()) <= Integer.parseInt(diag_max[0]))
                    .collect(Collectors.toList());
        }
        return products;
    }*/
