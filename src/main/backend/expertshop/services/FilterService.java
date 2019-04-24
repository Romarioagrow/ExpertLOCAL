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
                        List<Object> tv_params = new ArrayList<>((Collection<?>) entry.getValue());
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

    private void sortProducts(List<Product> products, Map<String, Object> params) {
        Object sort = extractParamValue(params, "sortBy", "sortOrder");
        switch (sort.toString()) {
            case "lowest"   -> products.sort(Comparator.comparingLong(Product::getPrice));
            case "highest"  -> {
                products.sort(Comparator.comparingLong(Product::getPrice));
                Collections.reverse(products);
            }
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


/*private void sortMin(List<Product> products, Map.Entry<String, Object> entry) {
        log.info("SSSSSSSSSUUUUUUUAAAKKAKAKAK"+(String) entry.getValue());
        products = products.stream().filter(product -> product.getPrice() >= Integer.parseInt((String) entry.getValue())).collect(Collectors.toList());
    }*/

    /*private List<Product> filterByPrice(List<Product> products, Map<String, Map<String, String[]>> params) {
        if (params.get("sortmin") != null )
        {
            Map<String, String[]> sortmin = params.get("sortmin");
            products = products.stream()
                    .filter(product -> product.getPrice() >= Integer.parseInt(sortmin[0]))
                    .collect(Collectors.toList());
        }
        if (params.get("sortmax") != null)
        {
            Map<String, String[]> sortmax = params.get("sortmax");
            products = products.stream()
                    .filter(product -> product.getPrice() <= Integer.parseInt(sortmax[0]))
                    .collect(Collectors.toList());
        }
        return products;
    }*/

        /*for (Map.Entry<String, Object> entry : params.entrySet())
        {

            System.out.println(entry.getKey() + "/" + entry.getValue());
        }*/

        /*for (Object item : params) {

        }
*/
/*Stream stream*/


       /* boolean result = switch(info) {
            case TRUE -> true;
            case FALSE -> false;
            case FILE_NOT_FOUND -> throw new UncheckedIOException(
                    "This is ridiculous!",
                    new FileNotFoundException());
            // as we'll see in "Exhaustiveness", `default` is not necessary
            default -> throw new IllegalArgumentException("Seriously?!");
        };*/

/*
products = filterByPrice(products, params);
        products = filterByManufacturer(products, params);

        if (req_type.equals("tv")) { //if(type("tv"))
            //products = filterByDiagonal(products, params); //if(present("diagonal")) diagonal.lenght != null
            //products = filterByResolution(products, params);

            products = filterByDisplayParams(products, params);
            products = filterByTvParams(products, params);
        }

        if (req_type.equals("stoves")) {
            products = filterByStoveDimensions(products, params);
*/
//}*/
    /*private List<Product> filterByDisplayParams(List<Product> products, Map<String, Map<String, String[]>> params) {

    }

    private List<Product> filterByStoveDimensions(List<Product> products, Map<String, Map<String, String[]>> params) {
        Map<String, String[]> stoveDimensions = params.get("stoveDimensions");
    }


    private void sortProducts(List<Product> products, Map<String, Map<String, String[]>> params) {
        Map<String, String[]> sortBy = params.get("sortBy");
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
    }*/

    /*
    private List<Product> filterByPrice(List<Product> products, Map<String, Map<String, String[]>> params) {
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

    private List<Product> filterByManufacturer(List<Product> products, Map<String, Map<String, String[]>> params) {
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

    private List<Product> filterByDiagonal(List<Product> products, Map<String, Map<String, String[]>> params) {
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

    private List<Product> filterByResolution(List<Product> products, Map<String, Map<String, String[]>> params) {
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

    private List<Product> filterByTvParams(List<Product> products, Map<String, Map<String, String[]>> params) {
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
    }*/


