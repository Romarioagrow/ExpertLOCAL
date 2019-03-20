package expertshop.controllers;
import expertshop.domain.Product;
import expertshop.domain.lists.Categories;
import expertshop.domain.lists.Types;
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

        ///V ЛОГИКУ ОБРАБОТКИ ФИЛЬТРОВ В СЕРВИС!!!
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



}
