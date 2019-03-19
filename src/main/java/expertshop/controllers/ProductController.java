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
import org.springframework.web.bind.annotation.*;
import java.util.*;

////--> ПЕРЕНЕСТИ КОНТРОЛЛЕРЫ ПО КАТЕГОРИИ ТОВАРОВ
////--> ВЫНЕСТИ СЛОЖНУЮ ЛОГИКУ ИЗ КОНТРОЛЛЕРОВ В СЕРВИСЫ

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

    //////////////////////////
    // Категория электроника
    @GetMapping("/electronics")
    public String showAllElectronics(
            Model model,
            @RequestParam(required = false, defaultValue = "") String sortby,
            /// в массив ...params
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country)
    {
        List<Product> products;

        /// ЛОГИКУ ОБРАБОТКИ ФИЛЬТРОВ В СЕРВИС!!!
        /// В boolean МЕТОД ПРОВЕРКИ УСЛОВИЙ!!!
        if ((!sortmin.isEmpty() | !sortmax.isEmpty() | !brand.isEmpty() | !country.isEmpty())
        ){
            Map<String, String> allFilterParams = new LinkedHashMap<String, String>();

            ///V добавить в метод фильтр пустых параметров
            /// ОПТИМИЗИРОВАТЬ ФОРМУ!
            allFilterParams.put("brand", brand);
            allFilterParams.put("country", country);
            allFilterParams.put("sortmin", sortmin);
            allFilterParams.put("sortmax", sortmax);
            allFilterParams.values().removeIf(String::isEmpty);

            // Проверка и обработка фильтров, наполнение модели
            products = filterService.mainFilterResolver(allFilterParams);

            /// В mainFilterResolver()!!!
            /*sortService.sorted(products, sortby);*/

            model.addAttribute("products", products);
            return "electronics";
        }
        else {
            products = productRepo.findByCategory(Categories.Electronics);

            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "electronics";
        }
    }
    ////////////////////////////////////////////////////

    /// В путь /electronics/tv
    @GetMapping("/tv")
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