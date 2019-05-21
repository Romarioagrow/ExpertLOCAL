package expertshop.controllers;
import expertshop.domain.Order;
import expertshop.domain.OrderedProduct;
import expertshop.domain.Product;
import expertshop.domain.User;
import expertshop.services.FilterService;
import expertshop.services.ProductService;

import expertshop.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Log
@RestController
@AllArgsConstructor
public class ServiceController {
    private final FilterService filterService;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/user/registration")
    public String addUser(@RequestBody Map<String, String> userDetails, User user, Model model)
    {
        log.info("Registration controller");
        if (!userService.addUser(user, userDetails))
        {
            log.info("return");
            model.addAttribute("message", userDetails.get("email"));
            return "fail";
        }
        log.info("Registration OK");
        return "success";
    }

    @PostMapping("/products/{reqType}")
    public List<Product> filterProducts(@RequestBody Map<String, Object> params, @PathVariable String reqType) {
        return filterService.filterProducts(params, reqType);
    }

    @PostMapping("/search")
    public List<Product> searchProducts(@RequestBody String searchRequest) {
        return productService.searchProducts(searchRequest);
    }

    @PostMapping("/order")
    private void addProductToOrder(@RequestBody String productID) {
        productService.addProductToOrder(productID);
    }

    @DeleteMapping("/order")
    private Order removeProductFromOrder(@RequestBody String productID) {
        return productService.removeProductFromOrder(productID);
    }

    @PutMapping("/order")
    private Queue<Object> changeAmount(@RequestBody Map<String, String> data) {
        return productService.changeAmount(data);
    }

    @PostMapping("/order/confirm")
    private void confirmOrder(@RequestBody Map<String, String> contacts) {
        productService.confirmOrder(contacts);
    }
}
