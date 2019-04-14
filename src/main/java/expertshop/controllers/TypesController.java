package expertshop.controllers;
import expertshop.domain.categories.Type;
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
public class TypesController {
    private final ProductService productService;

    @GetMapping("/")
    public String showAll(Model model)
    {
        model.addAttribute("currentProduct", "вся техника");
        model.addAttribute("products", productService.findAll());
        return "pages/main";
    }

    @GetMapping("/{req_product}")
    public String showByTypes(Model model, @PathVariable String req_product
    ){
        log.info("Type: " + req_product);

        model.addAttribute(req_product, "type");
        model.addAttribute("currentProduct", req_product);
        model.addAttribute("products", productService.findProducts(Type.valueOf(req_product)));
        return "pages/main";
    }
}
