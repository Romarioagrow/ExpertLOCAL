package expertshop.controllers;
import expertshop.domain.Order;
import expertshop.domain.User;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.repos.ProductRepo;
import expertshop.services.OrderService;
import expertshop.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log
@Controller
@AllArgsConstructor
public class CategoriesController {
    private final ProductService productService;
    private final OrderService orderService;
    private final ProductRepo productRepo;

    @GetMapping("/supplier")
    public String supplier(Model model, @AuthenticationPrincipal User user) {
        return "pages/supplier";
    }
    @PostMapping("/supplier")
    public String loadCSV (@RequestParam("file") MultipartFile file, Model model, @AuthenticationPrincipal User user) throws IOException {
        log.info(file.getOriginalFilename());

        productService.processFile(file);
        return "pages/supplier";
    }

    @GetMapping("/")
    public String showAll(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("url", "");
        model.addAttribute("order", order(user));
        //model.addAttribute("products", productService.findAll());
        return "pages/catalog";
    }

    @GetMapping("/categories/{category}")
    public String showByCategories(Model model, @PathVariable String category, @AuthenticationPrincipal User user)
    {
        log.info("Category: " + category);

        model.addAttribute("url", category);
        model.addAttribute("order", order(user));
        /*model.addAttribute("products", productService.findProducts(Category.valueOf(category)));*/
        return "pages/categories";
    }

    @GetMapping("/subcats/{req_subcategory}")
    public String showSubCategories( Model model, @PathVariable String req_subcategory, @AuthenticationPrincipal User user)
    {
        log.info("Category: " + req_subcategory);

        model.addAttribute("url", req_subcategory);
        model.addAttribute("order", order(user));
        /*model.addAttribute("products", productService.findProducts(SubCategory.valueOf(req_subcategory)));*/
        return "pages/products";
    }

    @GetMapping("/order")
    public String order(Model model, @AuthenticationPrincipal User user)
    {
        model.addAttribute("order", order(user));
        model.addAttribute("orderedProducts", orderService.showOrderedProducts(user));
        return "pages/order";
    }

    @GetMapping("/info/{productID}")
    public String showProduct(Model model, @PathVariable String productID, @AuthenticationPrincipal User user)
    {
        model.addAttribute("url", getCurrentURL(productID));
        model.addAttribute("order", order(user));
        /*model.addAttribute("product", productRepo.findByProductID(Integer.parseInt(productID)));*/

        productService.getOrderedID(user, model);
        return "pages/product";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        return "redirect:/hello";
    }

    @GetMapping("/subcats")
    public String subCategories( Model model) {
        return "redirect:/hello";
    }

    String getCurrentURL(String productID) {
        return productRepo.findByProductID(productID).getType().toString();
    }

    private Order order(User user) {
        return user != null ? orderService.getUserOrder(user.getUserID()) : orderService.getSessionOrder();
    }
}


