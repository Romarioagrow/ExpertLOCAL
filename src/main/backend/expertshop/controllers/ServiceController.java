package expertshop.controllers;
import expertshop.domain.Order;
import expertshop.domain.Product;
import expertshop.domain.User;
import expertshop.domain.dto.OrderContacts;
import expertshop.services.FilterService;
import expertshop.services.OrderService;
import expertshop.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Log
@RestController
@AllArgsConstructor
public class ServiceController {
    private final FilterService filterService;
    private final ProductService productService;
    private final OrderService orderService;

    /*@PostMapping("/products/{category}/{reqType}")
    public Page<Product>*//*Queue<Object>*//* filterProducts
            (Model model,
             @RequestBody Map<String, Object> params,
             @PathVariable String reqType,
             @AuthenticationPrincipal User user,
             @PathVariable String category,
             @PageableDefault(sort = {"supplier"}, direction = Sort.Direction.ASC, size = 15) Pageable pageable)
    {
        return filterService.filterProducts(params, reqType, *//*user,*//* pageable*//*, model*//*);
    }*/

    @PostMapping("/search")
    public List<Product> searchProducts
            (@RequestBody String searchRequest)
    {
        return productService.searchProducts(searchRequest);
    }

    /*@PostMapping("/getOrder")
    private Integer addProductToOrder
            (@AuthenticationPrincipal User user, @RequestBody String productID)
    {
        return orderService.addProductToOrder(productID, user);
    }*/
    @DeleteMapping("/getOrder")
    private Order removeProductFromOrder
            (@AuthenticationPrincipal User user, @RequestBody String productID)
    {
        return orderService.removeProductFromOrder(user, productID);
    }
    @PutMapping("/getOrder")
    private Queue<Object> changeAmount
            (@AuthenticationPrincipal User user, @RequestBody Map<String, String> data)
    {
        return orderService.changeAmount(user, data);
    }

    @PostMapping("/getOrder/confirm")
    private Set<String> confirmOrder
            (@AuthenticationPrincipal User user, @Valid @RequestBody OrderContacts contacts, BindingResult validResult)
    {
        if (validResult.hasErrors()) {
            return ControllerService.getValidErrorsSet(validResult);
        }
        else {
            orderService.confirmOrder(contacts, user);
            return null;
        }
    }
}
