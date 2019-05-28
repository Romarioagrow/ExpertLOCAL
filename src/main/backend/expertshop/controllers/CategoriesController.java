package expertshop.controllers;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.repos.OrderRepo;
import expertshop.services.OrderService;
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
    private final OrderService orderService;

    @GetMapping("/")
    public String showAll(Model model) {
        return "redirect:/hello";
    }

    @GetMapping("/hello")
    public String showHelloPage(Model model)
    {
        model.addAttribute("url", "");
        model.addAttribute("order", orderService.getCurrentOrder());
        model.addAttribute("products", productService.findAll());
        return "pages/hello";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        return "redirect:/hello";
    }

    @GetMapping("/subcats")
    public String subCategories( Model model) {
        return "redirect:/hello";
    }

    @GetMapping("/categories/{category}")
    public String showByCategories(Model model, @PathVariable String category
    ){
        log.info("Category: " + category);

        model.addAttribute("url", category);
        model.addAttribute("order", orderService.getCurrentOrder());
        model.addAttribute("products", productService.findProducts(Category.valueOf(category)));
        return "pages/categories";
    }

    @GetMapping("/subcats/{req_subcategory}")
    public String showSubCategories( Model model, @PathVariable String req_subcategory)
    {
        log.info("Category: " + req_subcategory);

        model.addAttribute("url", req_subcategory);
        model.addAttribute("order", orderService.getCurrentOrder()/*orderRepo.findBySessionUUID(productService.getSessionID())*/);
        model.addAttribute("products", productService.findProducts(SubCategory.valueOf(req_subcategory)));
        return "pages/main";
    }

    @GetMapping("/order")
    public String order(Model model)
    {
        model.addAttribute("order", orderService.getCurrentOrder());
        model.addAttribute("orderedProducts", productService.showOrderedProducts());
        return "pages/order";
    }
}


