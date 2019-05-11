package expertshop.controllers;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log
@Controller
@AllArgsConstructor
public class CategoriesController {
    private final ProductService productService;

    @GetMapping("/")
    public String showAll(Model model) {
        return "redirect:/hello";
    }

    @GetMapping("/hello")
    public String showHelloPage(Model model)
    {
        model.addAttribute("url", "");
        model.addAttribute("products", productService.findAll());
        return "pages/hello";
    }

    @GetMapping("/categories")
    public String categories(Model model)
    {
        model.addAttribute("products", productService.findAll());
        return "pages/main";
    }
    @GetMapping("/categories/{req_category}")
    public String showByCategories(Model model, @PathVariable String req_category
    ){
        log.info("Category: " + req_category);

        model.addAttribute("url", req_category);
        model.addAttribute("products", productService.findProducts(Category.valueOf(req_category)));
        return "pages/main";
    }

    @GetMapping("/subcats")
    public String subCategories( Model model)
    {
        model.addAttribute("products", productService.findAll());
        return "pages/main";
    }
    @GetMapping("/subcats/{req_subcategory}")
    public String showSubCategories( Model model, @PathVariable String req_subcategory)
    {
        log.info("Category: " + req_subcategory);

        model.addAttribute("url", req_subcategory);
        model.addAttribute("products", productService.findProducts(SubCategory.valueOf(req_subcategory)));
        return "pages/main";
    }
}


