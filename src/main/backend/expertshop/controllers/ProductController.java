package expertshop.controllers;
import expertshop.domain.Order;
import expertshop.domain.User;
import expertshop.repos.ProductRepo;
import expertshop.services.OrderService;
import expertshop.services.ProductService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final OrderService orderService;
    private final ProductService productService;
    private final ProductRepo productRepo;

    @GetMapping("{category}/{requiredProduct}")
    public String showByTypes(Model model,
                              @PathVariable String category,
                              @PathVariable String requiredProduct,
                              @AuthenticationPrincipal User user,
                              @PageableDefault(sort = {"originalPic"}, direction = Sort.Direction.ASC, size = 15) Pageable pageable)
    {
        String url = "/products/"+category+"/"+requiredProduct;
        String request = requiredProduct.replaceAll("_", " ").toLowerCase();
        String[] path = {StringUtils.capitalize(category), StringUtils.capitalize(request)};

        model.addAttribute("url",   url);
        model.addAttribute("path",  path);
        model.addAttribute("order", getOrder(user));
        model.addAttribute("page",  productService.findProducts(request, pageable, model));
        model.addAttribute("orderedProductsID", productService.getOrderedID(user));
        model.addAttribute("price", productService.getMinMaxPrice(request));
        return "pages/products";
    }

    @GetMapping("/info/{productID}")
    public String showProduct(Model model, @PathVariable String productID, @AuthenticationPrincipal User user)
    {
        model.addAttribute("url", getCurrentURL(productID));
        model.addAttribute("order", getOrder(user));
        model.addAttribute("product", productRepo.findByProductID(productID));
        model.addAttribute("anno", productService.displayAnnotation(productID, model));
        model.addAttribute("orderedProductsID", productService.getOrderedID(user));
        return "pages/product";
    }

    Order getOrder(User user) {
        return user != null ? orderService.getUserOrder(user.getUserID()) : orderService.getSessionOrder();
    }

    String getCurrentURL(String productID) {
        return productRepo.findByProductID(productID).getOriginalType();
    }
}

