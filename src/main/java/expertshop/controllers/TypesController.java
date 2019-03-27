package expertshop.controllers;
import expertshop.domain.categories.Type;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class TypesController {
    private final ProductsController productController;

    //ELECTRONICS
    /**/

    /**/
    //KITCHEN
    @GetMapping("/fridges")
    public String showFridges(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.Fridge, model, sortmin, sortmax, brand, country, sortby);
        return "pages/kitchen/fridges";
    }
    @GetMapping("/ovens")
    public String showOvens(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.Oven, model, sortmin, sortmax, brand, country, sortby);
        return "pages/kitchen/ovens";
    }

    /**/
    //HOME
    @GetMapping("/washing-machines")
    public String showWashingMachine(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.WashingMachine, model, sortmin, sortmax, brand, country, sortby);
        return "pages/home/washing-machines";
    }
    @GetMapping("/drying-machines")
    public String showClothesDryer(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.ClothesDryer, model, sortmin, sortmax, brand, country, sortby);
        return "pages/home/drying-machines";
    }

    /**/
    //CLIMATE
    @GetMapping("/conditioners")
    public String showConditioners(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.Conditioner, model, sortmin, sortmax, brand, country, sortby);
        return "pages/climate/conditioners";
    }
    @GetMapping("/water-heaters")
    public String showWaterHeaters(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.WaterHeater, model, sortmin, sortmax, brand, country, sortby);
        return "pages/climate/water-heaters";
    }
    @GetMapping("/gas-heaters")
    public String showGasWaterHeater(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.GasWaterHeater, model, sortmin, sortmax, brand, country, sortby);
        return "pages/climate/gas-heaters";
    }
    @GetMapping("/electric-heaters")
    public String showElectricHeaters(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.ElectricHeaters, model, sortmin, sortmax, brand, country, sortby);
        return "pages/climate/electric-heaters";
    }
    @GetMapping("/ventilators")
    public String showVentilators(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.Ventilator, model, sortmin, sortmax, brand, country, sortby);
        return "pages/climate/ventilators";
    }

    /**/
    //COMPUTERS
    @GetMapping("/notebooks")
    public String showNotebooks(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.Notebook, model, sortmin, sortmax, brand, country, sortby);
        return "pages/computers/computers-notebooks";
    }
    @GetMapping("/monitors")
    public String showMonitors(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        productController.showProductsByType(Type.Projector, model, sortmin, sortmax, brand, country, sortby);
        return "pages/electronics/electronics";
    }

    /**/
    //PORTABLE
    @GetMapping("/photo")
    public String showPhoto(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
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

    /**/
    //SMART
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
