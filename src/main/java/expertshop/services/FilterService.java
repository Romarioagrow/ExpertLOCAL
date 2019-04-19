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

    public List<Product> filterProducts(Map<String, String[]> params, String req_type) {
        showReceivedParams(params);

        List<Product> products = productRepo.findByType(Type.valueOf(req_type));

        products = filterByPrice(products, params);
        products = filterByManufacturer(products, params);

        if (req_type.equals("tv")) {
            products = filterByDiagonal(products, params);
            products = filterByResolution(products, params);
            products = filterByTvParams(products, params);
        }

        sortProducts(products, params);

        log.info("After filter: " + products.size());
        return products;
    }

    private void sortProducts(List<Product> products, Map<String, String[]> params) {
        String[] sortBy = params.get("sortBy");
        if (sortBy[0].equals("lowest")) {
            products.sort(Comparator.comparingLong(Product::getPrice));
        }
        if (sortBy[0].equals("highest")) {
            products.sort(Comparator.comparingLong(Product::getPrice));
            Collections.reverse(products);
        }
        if (sortBy[0].equals("alphabet")) {
            products.sort(Comparator.comparing(Product::getBrand));
        }
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
            log.info("Resol filters: " + resolution.length);
            log.info("Resol checked: " + Arrays.toString(resolution));

            if (resolution.length == 3) {
                products = products.stream()
                        .filter(product -> (product.getResolution().contains(resolution[0]))
                                | (product.getResolution().contains(resolution[1]))
                                | (product.getResolution().contains(resolution[2])))
                        .collect(Collectors.toList());
            }
            if (resolution.length == 2) {
                products = products.stream()
                        .filter(product -> (product.getResolution().contains(resolution[0])) | (product.getResolution().contains(resolution[1])))
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
            log.info("TvFeat checked: " + Arrays.toString(tv_params));

            if (tv_params.length == 4)
            {
                products = products.stream()
                        .filter(product -> ((product.getTvFeatures() != null)
                                && (product.getTvFeatures().contains(tv_params[0]))
                                |  (product.getTvFeatures().contains(tv_params[1]))
                                |  (product.getTvFeatures().contains(tv_params[2]))
                                |  (product.getTvFeatures().contains(tv_params[3]))))
                        .collect(Collectors.toList());
            }
            if (tv_params.length == 3)
            {
                products = products.stream()
                        .filter(product -> ((product.getTvFeatures() != null)
                                && (product.getTvFeatures().contains(tv_params[0]))
                                |  (product.getTvFeatures().contains(tv_params[1]))
                                |  (product.getTvFeatures().contains(tv_params[2]))))
                        .collect(Collectors.toList());
            }
            if (tv_params.length == 2)
            {
                products = products.stream()
                        .filter(product -> ((product.getTvFeatures() != null)
                                && (product.getTvFeatures().contains(tv_params[0]))
                                |  (product.getTvFeatures().contains(tv_params[1]))))
                        .collect(Collectors.toList());
            }
            if (tv_params.length == 1)
            {
                products = products.stream()
                        .filter(product -> ((product.getTvFeatures() != null) && product.getTvFeatures().contains(tv_params[0])))
                        .collect(Collectors.toList());
            }
        }
        return products;
    }

    private void showReceivedParams (Map<String, String[]> params) {
        log.info("\nServer received params with args:");
        params.forEach((param, args) -> log.info(param + ":" + Arrays.toString(args)));
    }
}
