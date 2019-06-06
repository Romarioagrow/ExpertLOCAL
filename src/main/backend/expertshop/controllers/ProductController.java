package expertshop.controllers;
import expertshop.domain.categories.Type;
import expertshop.repos.ProductRepo;
import expertshop.services.OrderService;
import expertshop.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

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
    private final ProductRepo productRepo;
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping("/{reqProduct}")
    public String showByTypes(Model model, @PathVariable String reqProduct
    ){
        log.info("Type: " + reqProduct);

        model.addAttribute("url", reqProduct);
        model.addAttribute("order", orderService.getSessionOrder());
        model.addAttribute("products", productService.findProducts(Type.valueOf(reqProduct)));
        return "pages/main";
    }
}

