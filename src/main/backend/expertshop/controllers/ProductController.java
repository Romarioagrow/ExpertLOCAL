package expertshop.controllers;
import expertshop.domain.Order;
import expertshop.domain.User;
import expertshop.domain.categories.Type;
import expertshop.repos.OrderRepo;
import expertshop.repos.ProductRepo;
import expertshop.services.OrderService;
import expertshop.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Log
@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping("/{requiredProduct}")
    public String showByTypes(@PathVariable String requiredProduct, @AuthenticationPrincipal User user, Model model)
    {
        log.info("Type: " + requiredProduct);

        model.addAttribute("url", requiredProduct);
        model.addAttribute("order", order(user));
        model.addAttribute("products", productService.findProducts(Type.valueOf(requiredProduct)));

        productService.getOrderedID(user, model);
        return "pages/products";
    }

    Order order(User user) {
        return user != null ? orderService.getUserOrder(user.getUserID()) : orderService.getSessionOrder();
    }
}

