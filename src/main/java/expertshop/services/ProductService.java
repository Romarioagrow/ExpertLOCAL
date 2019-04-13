package expertshop.services;
import expertshop.domain.Product;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
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
public class ProductService {
    private final ProductRepo productRepo;
    private final FilterService filterService;

    public List<Product> filterProducts(Map<String, String[]> params) {
        filterService.showReceivedParams(params);
        log.info("\nServer received params with args:");
        params.forEach((param, args) -> log.info(param + ":" + Arrays.toString(args)));

        /*String requiredType = params.requiredType*/
        List<Product> products = productRepo.findByType(Type.tv);

        filterService.filterByPrice(products, params);
        String[] sortmin    = params.get("sortmin");
        String[] sortmax    = params.get("sortmax");
        if (!sortmin[0].isEmpty()) {
            products = products.stream().filter(product -> product.getPrice() >= Integer.parseInt(sortmin[0])).collect(Collectors.toList());
        }
        if (!sortmax[0].isEmpty()) {
            products = products.stream().filter(product -> product.getPrice() <= Integer.parseInt(sortmax[0])).collect(Collectors.toList());
        }

        filterService.filterByManufacturer(products, params);
        String[] brand      = params.get("brands");
        String[] country    = params.get("country");
        if (!brand[0].isEmpty() | !country[0].isEmpty()) {
            products = products.stream().filter(product -> product.getBrand().equals(brand[0]) | product.getCountry().equals(country[0])).collect(Collectors.toList());
        }

        filterService.filterByDiagonal(products, params);
        String[] diag_min   = params.get("diag_min");
        String[] diag_max   = params.get("diag_max");
        if (!diag_min[0].isEmpty()) {
            products = products.stream().filter(product -> Integer.parseInt(product.getDiagonal()) >= Integer.parseInt(diag_min[0])).collect(Collectors.toList());
        }
        if (!diag_max[0].isEmpty()) {
            products = products.stream().filter(product -> Integer.parseInt(product.getDiagonal()) <= Integer.parseInt(diag_max[0])).collect(Collectors.toList());
        }

        filterService.filterByResolution(products, params);
        String[] resolution = params.get("resolution");

        filterService.filterByTvParams(products, params);
        String[] tv_params  = params.get("params");

        log.info("After filter: " + products.size());

        return products;
    }

    public List<Product> findProducts(Category category) {
        return productRepo.findByCategory(category);
    }

    public List<Product> findProducts(SubCategory subCategory) {
        return productRepo.findBySubCategory(subCategory);
    }

    public List<Product> findProducts(Type type) {
        return productRepo.findByType(type);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

}

//////////////////////////////////////////////
    /*public void constructAndFilter(Category category, Model model, String sortmin, String sortmax, String brand, String country, String sortby) {
        List<Product> products = productRepo.findByCategory(category);

        if (formIsActive(sortmin, sortmax, brand, country)) {
            filterProducts(model, sortmin, sortmax, brand, country, products);
        }
        else {
            model.addAttribute("products", products);
        }
    }
    public void constructAndFilter(SubCategory subCategory, Model model, String sortmin, String sortmax, String brand, String country, String sortby) {
        List<Product> products = productRepo.findBySubCategory(subCategory);

        if (formIsActive(sortmin, sortmax, brand, country)) {
            filterProducts(model, sortmin, sortmax, brand, country, products);
        }
        else {
            model.addAttribute("products", products);
        }
    }
    public void constructAndFilter(Type type, Model model, String sortmin, String sortmax, String brand, String country, String sortby) {
        List<Product> products = productRepo.findByType(type);

        if (formIsActive(sortmin, sortmax, brand, country)) {
            filterProducts(model, sortmin, sortmax, brand, country, products);
        }
        else {
            model.addAttribute("products", products);
        }
    }

    public void showAllProducts(Model model, String sortmin, String sortmax, String brand, String country, String sortby) {
        List<Product> products = productRepo.findAll();

        if (formIsActive(sortmin, sortmax, brand, country)) {
            filterProducts(model, sortmin, sortmax, brand, country, products);
        }
        else {
            model.addAttribute("products", products);
        }
    }

    private void filterProducts(Model model, String sortmin, String sortmax, String brand, String country, List<Product> products) {
        *//*StringBuilder filters = new StringBuilder();*//*

        if (!sortmin.isEmpty()) {
            products = products.stream().filter(product -> product.getPrice() >= Integer.parseInt(sortmin)).collect(Collectors.toList());
            *//*filters.append(" не дешевле ").append(sortmin);*//*
        }
        if (!sortmax.isEmpty()) {
            products = products.stream().filter(product -> product.getPrice() <= Integer.parseInt(sortmax)).collect(Collectors.toList());
            *//*filters.append(" не дороже ").append(sortmax);*//*
        }
        if (!brand.isEmpty() | !country.isEmpty()) {
            products = products.stream().filter(product -> product.getBrand().equals(brand) | product.getCountry().equals(country)).collect(Collectors.toList());
            *//*if (!country.isEmpty()) filters.append(" страна ").append(country);
            if (!brand.isEmpty()) filters.append(" бренд ").append(brand);*//*
        }

        *//*String appliedFilters = filters.toString();
        if (!appliedFilters.isEmpty()) model.addAttribute("appliedFilters", appliedFilters);*//*

        model.addAttribute("products", products);
    }

    private boolean formIsActive(String sortmin, String sortmax, String brand, String country) {
        return (sortmin != null | sortmax != null | brand != null | country != null);
    }*/





