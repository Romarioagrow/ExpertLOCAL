package expertshop.controllers;
import expertshop.domain.Product;
import expertshop.domain.categories.Category;
import expertshop.repos.ProductRepo;
import expertshop.services.FilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class CategoriesController {
    private final FilterService filterService;
    private final ProductRepo productRepo;

    @GetMapping("/test")
    public String showmda(
            Model model
    ){
        List<Product> products = productRepo.findAll();

        model.addAttribute("products", products);
        return "pages/test";
    }

    @GetMapping("/lol")
    public String shodwmda(
            Model model
    ){

        return "pages/lol";
    }

    @PostMapping("/test")
    public String showlmda(
            Model model
    ){
        return "pages/test";
    }


    @GetMapping("/")
    public String showAll(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country,
            @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.showAllProducts(model, sortmin, sortmax, brand, country, sortby);
        model.addAttribute("currentProduct", "вся техника");
        return "pages/main";
    }

    @GetMapping("/electronics-all")
    public String showElectronics(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country,
            @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby)
    {
        filterService.constructAndFilter(Category.Electronics, model, sortmin, sortmax, brand, country, sortby);
        model.addAttribute("currentProduct", "электроника");
        return "pages/main";
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
        filterService.constructAndFilter(Category.KitchenEquipment, model, sortmin, sortmax, brand, country, sortby);
        model.addAttribute("currentProduct", "техника для кухни");
        return "pages/main";
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
        filterService.constructAndFilter(Category.HomeEquipment, model, sortmin, sortmax, brand, country, sortby);
        model.addAttribute("currentProduct", "техника для дома");
        return "pages/main";
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
        filterService.constructAndFilter(Category.ClimateControl, model, sortmin, sortmax, brand, country, sortby);
        model.addAttribute("currentProduct", "климатическая техника");
        return "pages/main";
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
        filterService.constructAndFilter(Category.Computers, model, sortmin, sortmax, brand, country, sortby);

        model.addAttribute("currentProduct", "компьютеры");
        return "pages/main";
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
        filterService.constructAndFilter(Category.Portable, model, sortmin, sortmax, brand, country, sortby);
        model.addAttribute("currentProduct", "портативная техника");
        return "pages/main";
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
        filterService.constructAndFilter(Category.Smart, model, sortmin, sortmax, brand, country, sortby);
        model.addAttribute("currentProduct", "smart-техника");
        return "pages/main";
    }


}
