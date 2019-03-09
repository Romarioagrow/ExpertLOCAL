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
    public String showProducts(
            @RequestParam (required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findAll();

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "main";
    }

    /////////////////////////// // Категория электроника
    @GetMapping("/electronics")
    public String showAllElectronics(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByCategory(Categories.Electronics);

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "electronics";
    }
    @GetMapping("/tv") /// В путь /electronics/tv
    public String showTV(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Types.TV);

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "main";
    }
    @GetMapping("/projectors")
    public String showProjectors(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Types.Projector);

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "main";
    }

    ///////////////////////
    @GetMapping("/kitchen")
    public String showAllKitchen(
            @RequestParam(required = false, name = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByCategory(Categories.Kitchen);

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "main";
    }
    @GetMapping("/fridges")
    public String showFridges(
            @RequestParam(required = false, name = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Types.Fridge);

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "main";
    }
    @GetMapping("/ovens")
    public String showOvens(
            @RequestParam(required = false, name = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Types.Oven);

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "main";
    }
}
