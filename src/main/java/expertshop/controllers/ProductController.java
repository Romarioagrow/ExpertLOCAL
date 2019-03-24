package expertshop.controllers;
import expertshop.domain.Product;
import expertshop.repos.ProductRepo;
import expertshop.services.FilterService;
import expertshop.services.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/// ДЛЯ ВСЕХ КОНТРОЛЛЕРОВ!!!!
/// НАСЛЕДОВАТЬ, ОБЩИЕ МЕТОДЫ!!!
/// CategoryController, SubCategoryController, TypeController и ProductController!!!

@Controller
public class ProductController {
    private final ProductRepo productRepo;
    private final SortService sortService;
    private final FilterService filterService;
    @Autowired
    public ProductController(ProductRepo productRepo, SortService sortService, FilterService filterService) {
        this.productRepo = productRepo;
        this.sortService = sortService;
        this.filterService = filterService;
    }

    @GetMapping("/")
    public String showProducts(
            Model model,
            @RequestParam (required = false, defaultValue = "") String sortby,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/main";
        }
        else {
            products = productRepo.findAll();
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/main";
        }
    }

    private boolean formIsActive(String sortmin, String sortmax, String brand, String country) {
        return (!sortmin.isEmpty() | !sortmax.isEmpty() | !brand.isEmpty() | !country.isEmpty());
    }
}