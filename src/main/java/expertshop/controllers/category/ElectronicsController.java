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
public class ElectronicsController{
    private final ProductController productController;

    @GetMapping("/electronics")
    public String showElectronics(Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country,
            @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby)
    {
        productController.showProductsByCategory(Category.Electronics, model, sortmin, sortmax, brand, country, sortby);
        return "pages/electronics/electronics";
    }

    @GetMapping("/tv")
    public String showTV(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.TV, model, sortmin, sortmax, brand, country, sortby);
        return "pages/electronics/electronics";
    }

    @GetMapping("/multimedia")
    public String showMultimedia(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.Multimedia, model, sortmin, sortmax, brand, country, sortby);
        return "pages/electronics/electronics";
    }

    @GetMapping("/sputnik")
    public String showSputnik(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsBySubCategory(SubCategory.Sputnik, model, sortmin, sortmax, brand, country, sortby);
        return "pages/electronics/electronics";
    }
}


    /*@GetMapping("/projectors")
    public String showProjectors(Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.Projector, model, sortmin, sortmax, brand, country, sortby);
        return "pages/electronics/electronics";
    }

    @GetMapping("/monitors")
    public String showMonitors(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Type.Monitor);

        sortService.sorted(products, sortby);

        model.addAttribute("products", products);
        return "/main/resources/templates/pages/computers/monitors.ftl";
    }

    @GetMapping("/tv-4k")
    public String showTV4K(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Type.TV);

        model.addAttribute("products", products);
        return "pages/electronics/tv";
    }*/


///V ЛОГИКУ ОБРАБОТКИ ФИЛЬТРОВ В СЕРВИС!!!
///V В boolean МЕТОД ПРОВЕРКИ УСЛОВИЙ!!!
