package expertshop.controllers;
import expertshop.domain.Product;
import expertshop.domain.lists.Category;
import expertshop.repos.ProductRepo;
import expertshop.services.FilterService;
import expertshop.services.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;
///НАСЛЕДОВАТЬ, метод
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
        /*List<Product> products = productRepo.findAll();

        sortService.sorted(products, sortby);

        model.addAttribute("products", products);

        return "pages/main";*/

        List<Product> products;

        /*if ((!sortmin.isEmpty() | !sortmax.isEmpty() | !brand.isEmpty() | !country.isEmpty())
        ){
            Map<String, String> allFilterParams = new LinkedHashMap<String, String>();

            ///V добавить в метод фильтр пустых параметров
            /// ОПТИМИЗИРОВАТЬ ФОРМУ!
            allFilterParams.put("brand", brand);
            allFilterParams.put("country", country);
            allFilterParams.put("sortmin", sortmin);
            allFilterParams.put("sortmax", sortmax);
            allFilterParams.values().removeIf(String::isEmpty);

            // Проверка и обработка фильтров, наполнение модели
            products = filterService.mainFilterResolver(allFilterParams);

            /// В mainFilterResolver()!!!
            *//*sortService.sorted(products, sortby);*//*

            model.addAttribute("products", products);
            return "pages/main";
        }*/
        //else {
            products = productRepo.findAll();

            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/main";
       // }

    }
}