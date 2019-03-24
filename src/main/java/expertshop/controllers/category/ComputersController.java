package expertshop.controllers.category;
import expertshop.domain.Product;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
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
public class ComputersController {
    private final ProductRepo productRepo;
    private final SortService sortService;
    private final FilterService filterService;

    @GetMapping("/computers-all")
    public String showAllComputers(Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            // Проверка и обработка фильтров, наполнение модели
            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, Category.Computers);

            // Сортировка наполненной модели
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/computers/computers";
        }
        else {
            products = productRepo.findByCategory(Category.Computers);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/computers/computers";
        }
    }

    @GetMapping("/computers-pc")
    public String showPC(Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, SubCategory.PC);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/computers/computers-pc";
        }
        else {
            products = productRepo.findBySubCategory(SubCategory.PC);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/computers/computers-pc";
        }
    }

    @GetMapping("/notebooks")
    public String showNotebooks(Model model,
                         @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, Type.Notebook);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/computers/notebooks";
        }
        else {
            products = productRepo.findByType(Type.Notebook);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/computers/computers-notebooks";
        }
    }

    @GetMapping("/computer-parts")
    public String showPCP(Model model,
                         @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, SubCategory.ComputerParts);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/computers/computers-parts";
        }
        else {
            products = productRepo.findBySubCategory(SubCategory.ComputerParts);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/computers/computers-parts";
        }
    }

    @GetMapping("/computers-office")
    public String showComputersOffice(Model model,
                         @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, SubCategory.ComputersOffice);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/computers/computers-office";
        }
        else {
            products = productRepo.findBySubCategory(SubCategory.ComputersOffice);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/computers/computers-office";
        }
    }

    @GetMapping("/computers-accessory")
    public String showComputersAccessory(Model model,
                         @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<Product> products;

        if (formIsActive(sortmin, sortmax, brand, country)) {
            Map<String, String> filterParams = new LinkedHashMap<>();

            filterService.collectParams(filterParams, brand, country, sortmin, sortmax);
            products = filterService.mainFilterResolver(filterParams, SubCategory.ComputersAccessory);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/computers/computers-accessory";
        }
        else {
            products = productRepo.findBySubCategory(SubCategory.ComputersAccessory);
            sortService.sorted(products, sortby);

            model.addAttribute("products", products);
            return "pages/computers/computers-accessory";
        }
    }

    private boolean formIsActive(String sortmin, String sortmax, String brand, String country) {
        return (!sortmin.isEmpty() | !sortmax.isEmpty() | !brand.isEmpty() | !country.isEmpty());
    }
}
