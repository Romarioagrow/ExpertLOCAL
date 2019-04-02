package expertshop.services;
import expertshop.domain.Product;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.domain.categories.Type;
import expertshop.repos.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilterService {
    private final ProductRepo productRepo;

    public void constructAndFilter(Category category, Model model, String sortmin, String sortmax, String brand, String country, String sortby) {
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
            model.addAttribute("productsWithParams", products);
        }
    }

    public void showAllProducts(Model model, String sortmin, String sortmax, String brand, String country, String sortby) {
        List<Product> products = productRepo.findAll();

        if (formIsActive(sortmin, sortmax, brand, country)) {
            filterProducts(model, sortmin, sortmax, brand, country, products);
        }
        else {
            model.addAttribute("productsWithParams", products);
        }
    }

    private void filterProducts(Model model, String sortmin, String sortmax, String brand, String country, List<Product> products) {
        StringBuilder filters = new StringBuilder();

        if (!sortmin.isEmpty()) {
            products = products.stream().filter(product -> product.getPrice() >= Integer.parseInt(sortmin)).collect(Collectors.toList());
            filters.append(" не дешевле ").append(sortmin);
        }
        if (!sortmax.isEmpty()) {
            products = products.stream().filter(product -> product.getPrice() <= Integer.parseInt(sortmax)).collect(Collectors.toList());
            filters.append(" не дороже ").append(sortmax);
        }
        if (!brand.isEmpty() | !country.isEmpty()) {
            products = products.stream().filter(product -> product.getBrand().equals(brand) | product.getCountry().equals(country)).collect(Collectors.toList());
            if (!country.isEmpty()) filters.append(" страна ").append(country);
            if (!brand.isEmpty()) filters.append(" бренд ").append(brand);
        }

        String appliedFilters = filters.toString();
        if (!appliedFilters.isEmpty()) model.addAttribute("appliedFilters", appliedFilters);

        model.addAttribute("products", products);
    }

    private boolean formIsActive(String sortmin, String sortmax, String brand, String country) {
        return (sortmin != null | sortmax != null | brand != null | country != null);
    }
}

/// СОБРАТЬ ВСЕ ПАРАМЕТРЫ NOTNULL И ПРИМЕНИТЬ ИХ В STREAM ФИЛЬТР!!!
/*productsWithParams = productsWithParams.stream()
 *//*.filter(productWrap -> Objects.equals(productWrap.getProductParams().getResolution(), "4K 3840x2160"))*//* //resolution
                .filter(productWrap -> productWrap.getProduct().getPrice() >= Integer.parseInt(sortmin))//ifNotEquals("")
                .filter(productWrap -> productWrap.getProduct().getPrice() <= Integer.parseInt(sortmax))//ifNotEquals("")
                .filter(productWrap -> productWrap.getProduct().getBrand().equals(brand)) //ifNotEquals("")
                .filter(productWrap -> productWrap.getProduct().getCountry().equals(country))//ifNotEquals("")
                .collect(Collectors.toList());*/



