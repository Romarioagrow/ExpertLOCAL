package expertshop.controllers.category;
import expertshop.controllers.ProductController;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class KitchenController {
    private final ProductController productController;

    @GetMapping("/kitchen")
    public String showAllKitchen(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country,
            @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByCategory(Category.KitchenEquipment, model, sortmin, sortmax, brand, country, sortby);
        return "pages/kitchen/kitchen";
    }

    @GetMapping("/kitchen-big")
    public String showKitchenBig(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.KitchenBig, model, sortmin, sortmax, brand, country, sortby);
        return "pages/kitchen/kitchen-big";
    }

    @GetMapping("/kitchen-builtin")
    public String showKitchenBuiltin(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.KitchenBuiltin, model, sortmin, sortmax, brand, country, sortby);
        return "pages/kitchen/kitchen-builtin";
    }

    @GetMapping("/cooking-devices")
    public String showKitchenCookingDevice(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.CookingDevice, model, sortmin, sortmax, brand, country, sortby);
        return "pages/kitchen/cooking-devices";
    }

    @GetMapping("/kitchen-small")
    public String showKitchenSmall(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.KitchenSmall, model, sortmin, sortmax, brand, country, sortby);
        return "pages/kitchen/kitchen-small";
    }

    @GetMapping("/kitchen-accessory")
    public String showKitchenAccessory(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.KitchenAccessory, model, sortmin, sortmax, brand, country, sortby);
        return "pages/kitchen/kitchen-accessory";
    }

    /// Type
    /*@GetMapping("/fridges")
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
            return "kitchen";
        }
        else {
            products = productRepo.findByType(Type.Oven);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/kitchen/kitchen-big-ovens";
        }
    }*/
}
