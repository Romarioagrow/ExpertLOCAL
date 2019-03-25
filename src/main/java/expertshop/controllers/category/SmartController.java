package expertshop.controllers.category;
import expertshop.controllers.ProductController;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.Type;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SmartController {
    private final ProductController productController;

    @GetMapping("/smart-all")
    public String showAllSmart(
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

    @GetMapping("/smartphones")
    public String showSmartphones(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.SmartPhone, model, sortmin, sortmax, brand, country, sortby);
        return "pages/smart/smartphones";
    }

    @GetMapping("/tablets")
    public String showTablets(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.Tablet, model, sortmin, sortmax, brand, country, sortby);
        return "pages/smart/tablets";
    }

    @GetMapping("/smart-headers")
    public String showSmartHeaders(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.SmartHeaders, model, sortmin, sortmax, brand, country, sortby);
        return "pages/smart/smart-headers";
    }

    @GetMapping("/bluetooth")
    public String showAllPortable(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.BluetoothTech, model, sortmin, sortmax, brand, country, sortby);
        return "pages/smart/bluetooth";
    }
}
