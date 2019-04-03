package expertshop.controllers;
import expertshop.domain.categories.Type;
import expertshop.services.FilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class TypesController {
    private final FilterService filterService;

    @GetMapping("/tv-4K")
    public String showTV4K(
            Model model,
            @RequestParam(required = false, name = "sortmin") String sortmin,
            @RequestParam(required = false, name = "sortmax") String sortmax,
            @RequestParam(required = false, name = "brand") String brand,
            @RequestParam(required = false, name = "country") String country,
            @RequestParam(required = false, name = "sortby") String sortby
    ){
        filterService.constructAndFilter(Type.TV, model, sortmin, sortmax, brand, country, sortby);

        model.addAttribute("showTV", Type.TV);
        return "pages/test";
    }

    //KITCHEN
    @GetMapping("/fridges")
    public String showFridges(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.Fridge, model, sortmin, sortmax, brand, country, sortby);

        model.addAttribute("showFridges", Type.Fridge);
        model.addAttribute("currentProduct", "Холодильники");
        return "pages/test";
    }
    @GetMapping("/ovens")
    public String showOvens(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.Oven, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }

    //HOME
    @GetMapping("/washing-machines")
    public String showWashingMachine(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.WashingMachine, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }
    @GetMapping("/drying-machines")
    public String showClothesDryer(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.ClothesDryer, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }

    //CLIMATE
    @GetMapping("/conditioners")
    public String showConditioners(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.Conditioner, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }
    @GetMapping("/water-heaters")
    public String showWaterHeaters(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.WaterHeater, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }
    @GetMapping("/gas-heaters")
    public String showGasWaterHeater(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.GasWaterHeater, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }
    @GetMapping("/electric-heaters")
    public String showElectricHeaters(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.ElectricHeaters, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }
    @GetMapping("/ventilators")
    public String showVentilators(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.Ventilator, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }

    //COMPUTERS
    @GetMapping("/notebooks")
    public String showNotebooks(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.Notebook, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }
    @GetMapping("/monitors")
    public String showMonitors(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.Projector, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }

    //PORTABLE
    @GetMapping("/photo")
    public String showPhoto(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.PhotoCamera, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }
    @GetMapping("/video")
    public String showAllVideo(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.VideoCamera, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }
    @GetMapping("/radio")
    public String showPortableRadio(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.PortableRadio, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }
    @GetMapping("/mobile-phones")
    public String showMobilePhones(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.MobilePhone, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }

    //SMART
    @GetMapping("/smartphones")
    public String showSmartphones(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.SmartPhone, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }
    @GetMapping("/tablets")
    public String showTablets(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.Tablet, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }
    @GetMapping("/smart-headers")
    public String showSmartHeaders(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.SmartHeaders, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }
    @GetMapping("/bluetooth")
    public String showAllPortable(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        filterService.constructAndFilter(Type.BluetoothTech, model, sortmin, sortmax, brand, country, sortby);
        return "pages/test";
    }
}
