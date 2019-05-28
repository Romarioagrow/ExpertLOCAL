package expertshop.controllers;
import expertshop.domain.categories.Type;
import expertshop.repos.OrderRepo;
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

    @GetMapping("/{req_product}")
    public String showByTypes(Model model, @PathVariable String req_product
    ){
        log.info("Type: " + req_product);

        model.addAttribute("url", req_product);
        model.addAttribute("order", orderService.getCurrentOrder());
        model.addAttribute("products", productService.findProducts(Type.valueOf(req_product)));
        return "pages/main";
    }

    @GetMapping("/info/{product_id}")
    public String showProduct(Model model, @PathVariable String product_id)
    {
        String url = productRepo.findByProductID(Integer.parseInt(product_id)).getType().toString();

        model.addAttribute("url", url);
        model.addAttribute("order", orderService.getCurrentOrder());
        model.addAttribute("product", productRepo.findByProductID(Integer.parseInt(product_id)));
        return "pages/product";
    }
}
