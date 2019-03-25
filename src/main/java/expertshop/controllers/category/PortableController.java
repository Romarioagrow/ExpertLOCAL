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
public class PortableController {
    private final ProductController productController;

    @GetMapping("/portable-all")
    public String showAllPortable(
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

    @GetMapping("/photo")
    public String showPhoto(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.PhotoCamera, model, sortmin, sortmax, brand, country, sortby);
        return "pages/portable/portable-photo";
    }

    @GetMapping("/video")
    public String showAllVideo(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.VideoCamera, model, sortmin, sortmax, brand, country, sortby);
        return "pages/portable/portable-video";
    }

    @GetMapping("/radio")
    public String showPortableRadio(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.PortableRadio, model, sortmin, sortmax, brand, country, sortby);
        return "pages/portable/portable-radio";
    }

    @GetMapping("/mobile-phones")
    public String showMobilePhones(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.MobilePhone, model, sortmin, sortmax, brand, country, sortby);
        return "pages/portable/mobile-phones";
    }
}

