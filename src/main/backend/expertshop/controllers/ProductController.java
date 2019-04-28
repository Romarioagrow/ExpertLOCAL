package expertshop.controllers;

import expertshop.repos.ProductRepo;
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

    @GetMapping("/product/{product_id}")
    public String showAll(Model model, @PathVariable String product_id)
    {
        model.addAttribute("product", productRepo.findByProductID(Integer.parseInt(product_id)));

        String url = productRepo.findByProductID(Integer.parseInt(product_id)).getType().toString();
        log.info(url);
        model.addAttribute("url", url);
        return "pages/product";
    }
}
