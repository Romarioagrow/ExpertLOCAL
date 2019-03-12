package expertshop.controllers;
import expertshop.domain.lists.Categories;
import expertshop.domain.Product;
import expertshop.domain.lists.Types;
import expertshop.repos.ProductRepo;
import expertshop.services.FilterService;
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
    private final ProductRepo productRepo;

    private final SortService sortService;

    private final FilterService filterService;

    @Autowired
    public ProductController(ProductRepo productRepo, SortService sortService, FilterService filterService) {
        this.productRepo = productRepo;
        this.sortService = sortService;
        this.filterService = filterService;
    }

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
            /*@RequestParam(required = false, defaultValue = "") String ... params
            @RequestParam(required = false, defaultValue = "") String cheap, /// в массив ...params
            @RequestParam(required = false, defaultValue = "") String expen,
            @RequestParam(required = false, defaultValue = "") String brand,
            @RequestParam(required = false, defaultValue = "") String country*/

    {

        List<Product> products = productRepo.findByCategory(Categories.Electronics);

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "electronics";

        /*if (!cheap.equals("")) {
            *//*filterService.filter(params);*//*
            model.addAttribute("cheap", cheap);
            return "test";
        }
        else {
            List<Product> products = productRepo.findByCategory(Categories.Electronics);
            sortService.sorted(products, sortby);
            model.addAttribute("products", products);
            return "electronics";
        }*/
    }

    @GetMapping("/tv") /// В путь /electronics/tv
    public String showTV(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Types.TV);

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "tv";
    }
    @GetMapping("/projectors")
    public String showProjectors(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Types.Projector);

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "projectors";
    }
    @GetMapping("/monitors")
    public String showMonitors(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Types.Monitor);

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "monitors";
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
        return "kitchen";
    }
    @GetMapping("/fridges")
    public String showFridges(
            @RequestParam(required = false, name = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Types.Fridge);

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "fridges";
    }
    @GetMapping("/ovens")
    public String showOvens(
            @RequestParam(required = false, name = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Types.Oven);

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "ovens";
    }
}