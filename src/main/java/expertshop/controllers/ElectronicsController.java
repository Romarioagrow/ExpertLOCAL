package expertshop.controllers;
import expertshop.domain.Product;
import expertshop.domain.lists.Category;
import expertshop.domain.lists.Type;
import expertshop.repos.ProductRepo;
import expertshop.services.FilterService;
import expertshop.services.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

///V ЛОГИКУ ОБРАБОТКИ ФИЛЬТРОВ В СЕРВИС!!!
///V В boolean МЕТОД ПРОВЕРКИ УСЛОВИЙ!!!

@Controller
public class ElectronicsController {
    private final ProductRepo productRepo;
    private final SortService sortService;
    private final FilterService filterService;
    @Autowired
    public ElectronicsController(ProductRepo productRepo, SortService sortService, FilterService filterService) {
        this.productRepo = productRepo;
        this.sortService = sortService;
        this.filterService = filterService;
    }

    private boolean formIsActive(String sortmin, String sortmax, String brand, String country) {

        return (!sortmin.isEmpty() | !sortmax.isEmpty() | !brand.isEmpty() | !country.isEmpty());
    }

    // Категория электроника
    @GetMapping("/electronics")
    public String showAllElectronics(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country,
            @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            // Проверка и обработка фильтров, наполнение модели
            products = filterService.mainFilterResolver(filterParams, Category.Electronics);

            // Сортировка наполненной модели
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/electronics/electronics";
        }
        else {
            products = productRepo.findByCategory(Category.Electronics);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/electronics/electronics";
        }
    }

    @GetMapping("/tv")
    public String showTV(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Type.TV);

        sortService.sorted(products, sortby);

        model.addAttribute("products", products);
        return "pages/electronics/tv";
    }

    @GetMapping("/projectors")
    public String showProjectors(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Type.Projector);

        sortService.sorted(products, sortby);

        model.addAttribute("products", products);
        return "pages/computers/projectors";
    }

    @GetMapping("/monitors")
    public String showMonitors(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Type.Monitor);

        sortService.sorted(products, sortby);

        model.addAttribute("products", products);
        return "pages/electronics/monitors";
    }

    @GetMapping("/tv-4k")
    public String showTV4K(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Type.TV);

        model.addAttribute("products", products);
        return "pages/electronics/tv";
    }


}
