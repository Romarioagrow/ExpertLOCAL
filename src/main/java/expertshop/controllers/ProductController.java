package expertshop.controllers;
import expertshop.domain.lists.Categories;
import expertshop.domain.Product;
import expertshop.domain.lists.Types;
import expertshop.repos.ProductRepo;
import expertshop.services.FilterService;
import expertshop.services.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

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
            Model model,
            @RequestParam(required = false, defaultValue = "") String sortby,
            /// в массив ...params
            @RequestParam(required = false, name = "cheap", defaultValue = "") String cheap,
            @RequestParam(required = false, name = "expensive", defaultValue = "") String expensive,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country)
    {
        List<Product> products = new ArrayList<>();//= productRepo.findByCategory(Categories.Electronics);

    /*  sortService.sorted(products, sortby);
       model.addAttribute("products", products);
       return "electronics";
    */

        /// В boolean МЕТОД ПРОВЕРКИ УСЛОВИЙ!!!
        if ((!cheap.equals("") | !expensive.equals("") | !brand.equals("") | !country.equals(""))
        ){
            Map<String, String> filterParams = new LinkedHashMap<String, String>();


            /// ОПТИМИЗИРОВАТЬ!
            filterParams.put("cheap", cheap);
            filterParams.put("expensive", expensive);
            filterParams.put("brand", brand);
            filterParams.put("country", country);

            filterService.filterResolver(products, filterParams);


            /// В filterResolver()!!! для проверки и обработки фильтров { return filteredProducts
            /*products = filterService.filterResolver(products, filterParams);*/


            model.addAttribute("products", filterParams);
            /*model.addAttribute("products", products);*/
            return "test";
        }
        else {
            products = productRepo.findByCategory(Categories.Electronics);
            sortService.sorted(products, sortby);
            model.addAttribute("products", products);
            return "electronics";
        }
    }

    /*
    @GetMapping("/electronics")
    @RequestMapping(params = "filterParams", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public String showAllElectronics(
            Model model,
            @RequestParam(required = false, defaultValue = "") String sortby,
            @RequestBody String[] filterParams) throws MalformedURLException
            @RequestParam(required = false, defaultValue = "") String cheap, /// в массив ...params
            @RequestParam(required = false, defaultValue = "") String expen,
            @RequestParam(required = false, defaultValue = "") String brand,
            @RequestParam(required = false, defaultValue = "") String country)
    {
       List<Product> products = productRepo.findByCategory(Categories.Electronics);
       sortService.sorted(products, sortby);
       model.addAttribute("products", products);
       return "electronics";
        URL url = new URL("http://localhost:8080/electronics");
        Map<String,Object> params = new LinkedHashMap<>();
        HttpURLConnection conection = url
        if (!filterParams[1].equals("")) { /// В filterResolver()! для проверки и обработки фильтров { return filteredProducts
            //*filterService.filter(params);
            model.addAttribute("cheap", filterParams);
            return "test";
        }
        else {
            List<Product> products = productRepo.findByCategory(Categories.Electronics);
            sortService.sorted(products, sortby);
            model.addAttribute("products", products);
            return "electronics";
        }
    }
    */

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