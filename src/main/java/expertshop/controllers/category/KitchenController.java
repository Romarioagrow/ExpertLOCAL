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
public class KitchenController {
    private final ProductRepo productRepo;
    private final SortService sortService;
    private final FilterService filterService;

    @GetMapping("/kitchen")
    public String showAllKitchen(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            // Проверка и обработка фильтров, наполнение модели
            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, Category.KitchenEquipment);

            // Сортировка наполненной модели
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-all";
        }
        else {
            products = productRepo.findByCategory(Category.KitchenEquipment);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-all";
        }
    }

    @GetMapping("/kitchen-big")
    public String showKitchenBig(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, SubCategory.KitchenBig);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-big";
        }
        else {
            products = productRepo.findBySubCategory(SubCategory.KitchenBig);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-big";
        }
    }

    @GetMapping("/kitchen-builtin")
    public String showKitchenBuiltin(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, SubCategory.KitchenBuiltin);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-builtin";
        }
        else {
            products = productRepo.findBySubCategory(SubCategory.KitchenBuiltin);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-builtin";
        }
    }

    @GetMapping("/cooking-devices")
    public String showKitchenCookingDevice(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, SubCategory.CookingDevice);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/cooking-devices";
        }
        else {
            products = productRepo.findBySubCategory(SubCategory.KitchenBuiltin);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/cooking-devices";
        }
    }

    @GetMapping("/kitchen-small")
    public String showKitchenSmall(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, SubCategory.KitchenSmall);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-small";
        }
        else {
            products = productRepo.findBySubCategory(SubCategory.KitchenSmall);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-small";
        }
    }

    @GetMapping("/kitchen-accessory")
    public String showKitchenAccessory(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, SubCategory.KitchenAccessory);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-accessory";
        }
        else {
            products = productRepo.findBySubCategory(SubCategory.KitchenSmall);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-accessory";
        }
    }

    @GetMapping("/fridges")
    public String showFridges(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, Type.Fridge);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "kitchen-big-fridges";
        }
        else {
            products = productRepo.findByType(Type.Fridge);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-big-fridges";
        }
    }


    @GetMapping("/ovens")
    public String showOvens(
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, Type.Oven);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-all";
        }
        else {
            products = productRepo.findByType(Type.Oven);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-big-ovens";
        }
    }

    private boolean formIsActive(String sortmin, String sortmax, String brand, String country) {
        return (!sortmin.isEmpty() | !sortmax.isEmpty() | !brand.isEmpty() | !country.isEmpty());
    }

}
