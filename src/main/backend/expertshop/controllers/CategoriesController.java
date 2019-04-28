package expertshop.controllers;
import expertshop.domain.categories.Category;
import expertshop.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log
@Controller
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoriesController {
    private final ProductService productService;

    @GetMapping
    public String categories(Model model)
    {

        model.addAttribute("products", productService.findAll());
        return "pages/main";
    }

    @GetMapping("{req_category}")
    public String showByCategories(Model model, @PathVariable String req_category
    ){
        log.info("Category: " + req_category);

        model.addAttribute("url", "");
        model.addAttribute("products", productService.findProducts(Category.valueOf(req_category)));

        return "pages/main";
    }
}
