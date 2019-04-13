package expertshop.controllers;
import expertshop.domain.Product;

import expertshop.services.ProductService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import java.util.*;


@Log
@RestController
@AllArgsConstructor
public class FilterController {
    private final ProductService productService;

    @PostMapping("/tv")
    public List<Product> filterProducts(@RequestBody Map<String, String[]> params) {
        return productService.filterProducts(params);
    }
}

