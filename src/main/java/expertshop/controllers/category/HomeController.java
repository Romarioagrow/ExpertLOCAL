package expertshop.controllers.category;
import expertshop.domain.Product;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.domain.categories.Type;
import expertshop.repos.ProductRepo;
import expertshop.services.FilterService;
import expertshop.services.SortService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class HomeController {
    private final ProductRepo productRepo;
    private final SortService sortService;
    private final FilterService filterService;

    @GetMapping("/home")
    public String showAllHome(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            // Проверка и обработка фильтров, наполнение модели
            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, Category.HomeEquipment);

            // Сортировка наполненной модели
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/home/home-all";
        }
        else {
            products = productRepo.findByCategory(Category.HomeEquipment);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/home/home-all";
        }
    }

    @GetMapping("/home-washing")
    public String showWash(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, SubCategory.ForWashing);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/home/home-washing";
        }
        else {
            products = productRepo.findBySubCategory(SubCategory.ForWashing);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/home/home-washing";
        }
    }

    @GetMapping("/home-cleaning")
    public String showCleaning(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, SubCategory.ForCleaning);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/home/home-cleaning";
        }
        else {
            products = productRepo.findBySubCategory(SubCategory.ForCleaning);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/home/home-cleaning";
        }
    }

    @GetMapping("/home-clothes")
    public String showClothes(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, SubCategory.ForClothes);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/home/home-clothes";
        }
        else {
            products = productRepo.findBySubCategory(SubCategory.ForClothes);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/home/home-clothes";
        }
    }

    @GetMapping("/home-accessory")
    public String showHomeAccessory(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, SubCategory.HomeAccessory);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/home/home-accessory";
        }
        else {
            products = productRepo.findBySubCategory(SubCategory.HomeAccessory);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/home/home-accessory";
        }
    }

    @GetMapping("/washing-machine")///Type
    public String showWM(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Type.WashingMachine);
        sortService.sorted(products, sortby);

        model.addAttribute("products", products);
        return "pages/home/washing-machine";
    }

    @GetMapping("/drying-machine")///Type
    public String showCD(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Type.ClothesDryer);
        sortService.sorted(products, sortby);

        model.addAttribute("products", products);
        return "pages/home/clothes-dryer";
    }

    private boolean formIsActive(String sortmin, String sortmax, String brand, String country) {
        return (!sortmin.isEmpty() | !sortmax.isEmpty() | !brand.isEmpty() | !country.isEmpty());
    }
}
