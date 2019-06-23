package expertshop.controllers;
import expertshop.domain.Order;
import expertshop.domain.User;
import expertshop.products.ProductResolver;
import expertshop.repos.ProductRepo;
import expertshop.products.CatalogParser;
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
    private final CatalogParser catalogParser;
    private final ProductRepo productRepo;
    private final ProductResolver productResolver;

    @GetMapping("/supplier")
    public String supplier()
    {
        productResolver.resolveProductGroups();
        productResolver.resolveOriginalPrice();
        return "pages/supplier";
    }
    @PostMapping("/supplier")
    public String loadCSV (@RequestParam("file") MultipartFile file, Model model, @AuthenticationPrincipal User user) throws IOException {
        log.info(file.getOriginalFilename());

        catalogParser.processFile(file);
        return "pages/supplier";
    }

    @GetMapping("/supplier/pics")
    public String checkProductPics()
    {
        //catalogParser.checkProductPics();
        return "pages/supplier";
    }

    @GetMapping("/")
    public String showAll(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("url", "");
        model.addAttribute("order", getOrder(user));
        return "pages/catalog";
    }

    @GetMapping("/categories/{category}")
    public String showByCategories(Model model, @PathVariable String category, @AuthenticationPrincipal User user)
    {
        log.info("Category: " + category);

        model.addAttribute("url", category);
        model.addAttribute("order", getOrder(user));
        return "pages/categories";
    }

    @GetMapping("/subcats/{req_subcategory}")
    public String showSubCategories( Model model, @PathVariable String req_subcategory, @AuthenticationPrincipal User user)
    {
        log.info("Category: " + req_subcategory);

        model.addAttribute("url", req_subcategory);
        model.addAttribute("order", getOrder(user));
        return "pages/products";
    }

    @GetMapping("/order")
    public String getOrder(Model model, @AuthenticationPrincipal User user)
    {
        model.addAttribute("order", getOrder(user));
        model.addAttribute("orderedProducts", orderService.showOrderedProducts(user));
        return "pages/order";
    }

    @GetMapping("/categories")
    public String categories() {
        return "redirect:/hello";
    }

    @GetMapping("/subcats")
    public String subCategories() {
        return "redirect:/hello";
    }

    /*String getCurrentURL(String productID) {
        return productRepo.findByProductID(productID).getType().toString();
    }*/

    private Order getOrder(User user) {
        return user != null ? orderService.getUserOrder(user.getUserID()) : orderService.getSessionOrder();
    }
}


