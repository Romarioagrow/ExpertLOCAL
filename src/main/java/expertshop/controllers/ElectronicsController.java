/*
package expertshop.controllers;
import expertshop.domain.Product;
import expertshop.domain.lists.Types;
import expertshop.repos.ProductRepo;
import expertshop.services.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping(value = "/electronics")
public class ElectronicsController {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SortService sortService;

    @RequestMapping(value = "/tv")
    public String showTV(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model) {
        List<Product> products = productRepo.findByType(Types.TV);

        sortService.sorted(products, sortby);
        model.addAttribute("products", products);
        return "tv";
    }
}*/
