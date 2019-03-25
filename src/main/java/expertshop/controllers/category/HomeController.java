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
public class HomeController {
    private final ProductController productController;

    @GetMapping("/home")
    public String showAllHome(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country,
            @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByCategory(Category.HomeEquipment, model, sortmin, sortmax, brand, country, sortby);
        return "pages/home/home";
    }

    @GetMapping("/home-washing")
    public String showWash(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.ForWashing, model, sortmin, sortmax, brand, country, sortby);
        return "pages/home/home-washing";
    }

    @GetMapping("/home-cleaning")
    public String showCleaning(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.ForCleaning, model, sortmin, sortmax, brand, country, sortby);
        return "pages/home/home-cleaning";
    }

    @GetMapping("/home-clothes")
    public String showClothes(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.ForClothes, model, sortmin, sortmax, brand, country, sortby);
        return "pages/home/home-clothes";
    }

    @GetMapping("/home-accessory")
    public String showHomeAccessory(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.HomeAccessory, model, sortmin, sortmax, brand, country, sortby);
        return "pages/home/home-accessory";
    }

    ///Type
    /*@GetMapping("/washing-machine")
    public String showWM(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Type.WashingMachine);
        sortService.sorted(products, sortby);

        model.addAttribute("products", products);
        return "pages/home/washing-machine";
    }
    ///Type
    @GetMapping("/drying-machine")
    public String showCD(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Type.ClothesDryer);
        sortService.sorted(products, sortby);

        model.addAttribute("products", products);
        return "pages/home/clothes-dryer";
    }*/


}
