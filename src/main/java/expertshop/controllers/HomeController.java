package expertshop.controllers;
import expertshop.domain.Product;
import expertshop.domain.lists.Category;
import expertshop.domain.lists.SubCategory;
import expertshop.domain.lists.Type;
import expertshop.repos.ProductRepo;
import expertshop.services.FilterService;
import expertshop.services.SortService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class HomeController {
    private final ProductRepo productRepo;
    private final SortService sortService;
    private final FilterService filterService;

    public HomeController(ProductRepo productRepo, SortService sortService, FilterService filterService) {
        this.productRepo = productRepo;
        this.sortService = sortService;
        this.filterService = filterService;
    }

    @GetMapping("/home")///Category
    public String showHome(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByCategory(Category.HomeEquipment);

        sortService.sorted(products, sortby);

        model.addAttribute("products", products);
        return "pages/home/home";
    }

    @GetMapping("/wash")///SubCategory
    public String showTV(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findBySubCategory(SubCategory.Wash);

        sortService.sorted(products, sortby);

        model.addAttribute("products", products);
        return "pages/home/wash";
    }


    @GetMapping("/washing-machine")///Type
    public String showWM(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Type.WashingMachine);

        sortService.sorted(products, sortby);

        model.addAttribute("products", products);
        return "pages/home/washing-machine";
    }

    @GetMapping("/drying-machine")///Type
    public String showCD(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> products = productRepo.findByType(Type.ClothesDryer);

        sortService.sorted(products, sortby);

        model.addAttribute("products", products);
        return "pages/home/clothes-dryer";
    }
}
