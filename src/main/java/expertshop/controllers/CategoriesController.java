package expertshop.controllers;
import expertshop.domain.categories.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class CategoriesController {
    private final ProductController productController;

    @GetMapping("/electronics-all")
    public String showElectronics(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country,
            @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby)
    {
        productController.showProductsByCategory(Category.Electronics, model, sortmin, sortmax, brand, country, sortby);
        return "pages/electronics/electronics";
    }

    @GetMapping("/kitchen-all")
    public String showKitchenEquipment(
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

    @GetMapping("/home-all")
    public String showHomeEquipment(
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

    @GetMapping("/climate-all")
    public String showClimateControl(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country,
            @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByCategory(Category.ClimateControl, model, sortmin, sortmax, brand, country, sortby);
        return "pages/climate/climate";
    }

    @GetMapping("/computers-all")
    public String showComputers(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country,
            @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByCategory(Category.Computers, model, sortmin, sortmax, brand, country, sortby);
        return "pages/computers/computers";
    }

    @GetMapping("/portable-all")
    public String showPortable(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country,
            @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByCategory(Category.Portable, model, sortmin, sortmax, brand, country, sortby);
        return "pages/portable/portable";
    }

    @GetMapping("/smart-all")
    public String showSmart(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country,
            @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByCategory(Category.Smart, model, sortmin, sortmax, brand, country, sortby);
        return "pages/smart/smart";
    }
}
