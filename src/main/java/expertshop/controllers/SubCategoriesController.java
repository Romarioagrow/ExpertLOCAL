package expertshop.controllers;
import expertshop.domain.categories.SubCategory;
import expertshop.domain.categories.Type;
import expertshop.services.FilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SubCategoriesController {
    private final FilterService filterService;

    //Electronics
    @GetMapping("/tv")
    public String showTV(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.TV, model, sortmin, sortmax, brand, country, sortby);
        return "pages/electronics/tv";
    }
    @GetMapping("/multimedia")
    public String showMultimedia(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.Multimedia, model, sortmin, sortmax, brand, country, sortby);
        return "pages/electronics/multimedia";
    }
    @GetMapping("/sputnik")
    public String showSputnik(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.Sputnik, model, sortmin, sortmax, brand, country, sortby);
        return "pages/electronics/sputnik";
    }

    //Kitchen
    @GetMapping("/kitchen-big")
    public String showKitchenBig(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.KitchenBig, model, sortmin, sortmax, brand, country, sortby);
        return "pages/kitchen/kitchen-big";
    }
    @GetMapping("/kitchen-builtin")
    public String showKitchenBuiltin(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.KitchenBuiltin, model, sortmin, sortmax, brand, country, sortby);
        return "pages/kitchen/kitchen-builtin";
    }
    @GetMapping("/cooking-devices")
    public String showKitchenCookingDevice(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.CookingDevice, model, sortmin, sortmax, brand, country, sortby);
        return "pages/kitchen/cooking-devices";
    }
    @GetMapping("/kitchen-small")
    public String showKitchenSmall(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.KitchenSmall, model, sortmin, sortmax, brand, country, sortby);
        return "pages/kitchen/kitchen-small";
    }
    @GetMapping("/kitchen-accessory")
    public String showKitchenAccessory(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.KitchenAccessory, model, sortmin, sortmax, brand, country, sortby);
        return "pages/kitchen/kitchen-accessory";
    }

    //Home
    @GetMapping("/home-washing")
    public String showWashing(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.ForWashing, model, sortmin, sortmax, brand, country, sortby);
        return "pages/home/home-washing";
    }
    @GetMapping("/home-cleaning")
    public String showCleaning(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.ForCleaning, model, sortmin, sortmax, brand, country, sortby);
        return "pages/home/home-cleaning";
    }
    @GetMapping("/home-clothes")
    public String showClothes(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.ForClothes, model, sortmin, sortmax, brand, country, sortby);
        return "pages/home/home-clothes";
    }
    @GetMapping("/home-accessory")
    public String showHomeAccessory(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.HomeAccessory, model, sortmin, sortmax, brand, country, sortby);
        return "pages/home/home-accessory";
    }

    //Computers
    @GetMapping("/computers-pc")
    public String showPC(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.PC, model, sortmin, sortmax, brand, country, sortby);
        return "pages/computers/computers-pc";
    }
    @GetMapping("/computer-parts")
    public String showComputerParts(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.ComputerParts, model, sortmin, sortmax, brand, country, sortby);
        return "pages/computers/computer-parts";
    }
    @GetMapping("/computers-office")
    public String showComputersOffice(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.ComputersOffice, model, sortmin, sortmax, brand, country, sortby);
        return "pages/computers/computers-office";
    }
    @GetMapping("/computers-accessory")
    public String showComputersAccessory(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(SubCategory.ComputersAccessory, model, sortmin, sortmax, brand, country, sortby);
        return "pages/computers/computers-accessory";
    }
}
