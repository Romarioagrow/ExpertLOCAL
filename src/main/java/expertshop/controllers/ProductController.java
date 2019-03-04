package expertshop.controllers;
import expertshop.entities.Categories;
import expertshop.entities.Product;
import expertshop.repos.ProductRepo;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/")
    public String showProducts(Model model) {
        Iterable<Product> products = productRepo.findAll();

        model.addAttribute("products", products);

        return "main";
    }

    @GetMapping("/computers")
    public String showComputers(Model model) {
        List<Product> computers = (List<Product>) productRepo.findByCategory(Categories.Computers);

        model.addAttribute("computers", computers);

        return "computers";
    }

    @GetMapping("/electronics")
    public String showElectronics(
            @RequestParam(required = false, defaultValue = "") String sortby,
            Model model)
    {
        List<Product> electronics = (List<Product>) productRepo.findByCategory(Categories.Electronics);

        if (sortby != null && !sortby.isEmpty()) { /// В SORT SERVICE
            switch (sortby) {
                case "lowest":
                    electronics.sort(Comparator.comparing(Product::getPrice));
                    break;
                case "highest":
                    electronics.sort(Comparator.comparing(Product::getPrice));
                    Collections.reverse(electronics);
                    break;
            }
        }

        model.addAttribute("electronics", electronics);

        return "electronics";
    }

    @GetMapping("/mobile")
    public String showMobile(Model model) {
        List<Product> mobiles = (List<Product>) productRepo.findByCategory(Categories.Mobile);

        model.addAttribute("mobiles", mobiles);

        return "mobile";
    }
}
