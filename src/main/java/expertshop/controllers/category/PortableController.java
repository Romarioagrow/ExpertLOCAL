package expertshop.controllers.category;
import expertshop.domain.Product;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.Type;
import expertshop.repos.ProductRepo;
import expertshop.services.FilterService;
import expertshop.services.SortService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class PortableController {
    private final ProductRepo productRepo;
    private final SortService sortService;
    private final FilterService filterService;

    @GetMapping("/portable-all")
    public String showAllPortable(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            // Проверка и обработка фильтров, наполнение модели
            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, Category.Portable);

            // Сортировка наполненной модели
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/portable/portable";
        }
        else {
            products = productRepo.findByCategory(Category.Portable);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/portable/portable";
        }
    }

    @GetMapping("/photo")
    public String showPhoto(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            // Проверка и обработка фильтров, наполнение модели
            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, Type.PhotoCamera);

            // Сортировка наполненной модели
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/portable/portable-photo";
        }
        else {
            products = productRepo.findByType(Type.PhotoCamera);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/portable/portable-photo";
        }
    }

    @GetMapping("/video")
    public String showAllVideo(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            // Проверка и обработка фильтров, наполнение модели
            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, Type.VideoCamera);

            // Сортировка наполненной модели
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/portable/portable-video";
        }
        else {
            products = productRepo.findByType(Type.VideoCamera);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/portable/portable-video";
        }
    }

    @GetMapping("/radio")
    public String showPortableRadio(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            // Проверка и обработка фильтров, наполнение модели
            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, Type.PortableRadio);

            // Сортировка наполненной модели
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/portable/portable-radio";
        }
        else {
            products = productRepo.findByType(Type.PortableRadio);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/portable/portable-radio";
        }
    }

    @GetMapping("/mobile-phones")
    public String showMobilePhones(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            // Проверка и обработка фильтров, наполнение модели
            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, Type.MobilePhone);

            // Сортировка наполненной модели
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/portable/mobile-phones";
        }
        else {
            products = productRepo.findByType(Type.MobilePhone);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/portable/mobile-phones";
        }
    }

    private boolean formIsActive(String sortmin, String sortmax, String brand, String country) {
        return (!sortmin.isEmpty() | !sortmax.isEmpty() | !brand.isEmpty() | !country.isEmpty());
    }
}

