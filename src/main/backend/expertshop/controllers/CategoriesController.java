package expertshop.controllers;
import expertshop.domain.Order;
import expertshop.domain.User;
import expertshop.repos.ProductRepo;
import expertshop.repos.UserRepo;
import expertshop.services.OrderService;
import expertshop.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log
@Controller
@AllArgsConstructor
public class CategoriesController {
    private final ProductService    productService;
    private final OrderService      orderService;
    private final ProductRepo       productRepo;
    private final UserRepo userRepo;

    /*ORDER*/
    @GetMapping("/order")
    public String getOrder(Model model, @AuthenticationPrincipal User user)
    {
        if (user != null) {
            User reloadUser = userRepo.findByUserID(user.getUserID());
            model.addAttribute("bonus", reloadUser.getBonus());
            model.addAttribute("discount", orderService.calculateDiscount(reloadUser, getOrder(reloadUser)));
            model.addAttribute("order", getOrder(reloadUser));
            model.addAttribute("user", reloadUser);
            model.addAttribute("orderedProducts", orderService.showOrderedProducts(reloadUser));
        }
        model.addAttribute("orderedProducts", orderService.showOrderedProducts(user));
        model.addAttribute("order", getOrder(user));
        return "pages/order";
    }

    @GetMapping("/")
    public String showAll(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("url", "теле-видео-аудио");
        model.addAttribute("order", getOrder(user));
        return "pages/catalog";
    }

    @GetMapping("/categories")
    public String categories() {
        return "pages/catalog";
    }

    @GetMapping("/categories/{category}")
    public String showByCategories(Model model, @PathVariable String category, @AuthenticationPrincipal User user)
    {
        model.addAttribute("url", category);
        model.addAttribute("order", getOrder(user));
        model.addAttribute("groups", productService.displayCatalogGroups(category));
        log.info(category);
        return "pages/catalog";
    }

    /*@GetMapping("/subcats/{req_subcategory}")
    public String showSubCategories( Model model, @PathVariable String req_subcategory, @AuthenticationPrincipal User user)
    {
        //log.info("Category: " + req_subcategory);
        model.addAttribute("url", req_subcategory);
        model.addAttribute("order", getOrder(user));
        return "pages/products";
    }
    @GetMapping("/subcats")
    public String subCategories() {
        return "redirect:/hello";
    }*/

    private Order getOrder(User user) {
        return user != null ? orderService.getUserOrder(user.getUserID()) : orderService.getSessionOrder();
    }
}


