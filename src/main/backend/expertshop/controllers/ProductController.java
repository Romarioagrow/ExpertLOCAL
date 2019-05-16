package expertshop.controllers;
import expertshop.domain.categories.Type;
import expertshop.repos.OrderRepo;
import expertshop.repos.ProductRepo;
import expertshop.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Log
@Controller
@AllArgsConstructor
public class ProductController {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final ProductService productService;

    @GetMapping("/{req_product}")
    public String showByTypes(Model model, @PathVariable String req_product
    ){
        log.info("Type: " + req_product);

        model.addAttribute("url", req_product);
        if (!req_product.equals("null")) model.addAttribute("products", productService.findProducts(Type.valueOf(req_product)));
        return "pages/main";
    }

    @GetMapping("/product/{product_id}")
    public String showProduct(Model model, @PathVariable String product_id)
    {
        String url = productRepo.findByProductID(Integer.parseInt(product_id)).getType().toString();

        model.addAttribute("url", url);
        model.addAttribute("product", productRepo.findByProductID(Integer.parseInt(product_id)));
        return "pages/product";
    }

    @GetMapping("/order")
    public String order(Model model)
    {
        model.addAttribute("orderedProducts", productService.showOrderedProducts());
        model.addAttribute("order", orderRepo.findBySessionUUID(productService.getSessionID()));
        return "pages/order";
    }
}
