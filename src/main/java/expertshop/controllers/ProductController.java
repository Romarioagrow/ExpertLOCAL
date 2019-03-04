package expertshop.controllers;
import expertshop.entities.Categories;
import expertshop.entities.Product;
import expertshop.entities.Types;
import expertshop.repos.ProductRepo;
import expertshop.services.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/// ПЕРЕНЕСТИ КОНТРОЛЛЕРЫ ПО КАТЕГОРИИ ТОВАРОВ

@Controller
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SortService sortService;

    @GetMapping("/")
    public String showProducts(Model model) {
        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "main";
    }

    /////////////////////////// // Категория электроника
    @GetMapping("/electronics")
    public String showAllElectronics(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> electronics = (List<Product>) productRepo.findByCategory(Categories.Electronics);

        sortService.sorted(electronics, sortby);

        model.addAttribute("electronics", electronics);
        return "electronics";
    }
    @GetMapping("/tv") /// В путь /electronics/tv
    public String showTV(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> tv = (List<Product>) productRepo.findByType(Types.TV);

        sortService.sorted(tv, sortby);

        model.addAttribute("tv", tv);
        return "tv";
    }
    @GetMapping("/projectors")
    public String showProjectors(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> projectors = (List<Product>) productRepo.findByType(Types.Projector);

        sortService.sorted(projectors, sortby);

        model.addAttribute("projectors", projectors);
        return "projectors";
    }

    ///////////////////////
    @GetMapping("/kitchen")
    public String showAllKitchen(
            @RequestParam(required = false, name = "") String sortby,
            Model model)
    {
        List<Product> kitchens = (List<Product>) productRepo.findByCategory(Categories.Kitchen);

        sortService.sorted(kitchens, sortby);

        model.addAttribute("kitchens", kitchens);
        return "kitchen";
    }
    @GetMapping("/fridges")
    public String showFridges(
            @RequestParam(required = false, name = "") String sortby,
            Model model)
    {
        List<Product> fridges = (List<Product>) productRepo.findByType(Types.Fridge);

        sortService.sorted(fridges, sortby);

        model.addAttribute("fridges", fridges);
        return "fridges";
    }
    @GetMapping("/ovens")
    public String showOvens(
            @RequestParam(required = false, name = "") String sortby,
            Model model)
    {
        List<Product> ovens = (List<Product>) productRepo.findByType(Types.Oven);

        sortService.sorted(ovens, sortby);

        model.addAttribute("ovens", ovens);
        return "ovens";
    }
}
