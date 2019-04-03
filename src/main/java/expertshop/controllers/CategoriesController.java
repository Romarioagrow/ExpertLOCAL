package expertshop.controllers;
import expertshop.domain.categories.Category;
import expertshop.services.FilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class CategoriesController {
    private final FilterService filterService;

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
        return "pages/test";
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
        return "pages/test";
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
        return "pages/test";
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
        return "pages/test";
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
        return "pages/test";
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
        model.addAttribute("currentProduct", "компьютеры ");
        return "pages/test";
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
        return "pages/test";
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
        return "pages/test";
    }
}