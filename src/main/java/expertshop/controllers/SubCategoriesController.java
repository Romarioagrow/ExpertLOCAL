package expertshop.controllers;
import expertshop.domain.categories.SubCategory;
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
@RequestMapping("/subcategories")
public class SubCategoriesController {
    private final ProductService productService;

    @GetMapping("{req_subcategory}")
    public String showByCategories(
            Model model,
            @PathVariable String req_subcategory
    ){
        log.info("Subcategory: " + req_subcategory);

        model.addAttribute(req_subcategory, "category");
        model.addAttribute("currentProduct", req_subcategory);
        model.addAttribute("products", productService.findProducts(SubCategory.valueOf(req_subcategory)));

        return "pages/main";
    }
}
