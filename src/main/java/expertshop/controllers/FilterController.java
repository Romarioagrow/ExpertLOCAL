package expertshop.controllers;
import expertshop.domain.Product;
import expertshop.domain.categories.Type;
import expertshop.repos.ProductRepo;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import java.util.*;

@Log
@RestController
@AllArgsConstructor
public class FilterController {
    private final ProductRepo productRepo;

    @PostMapping("/tv")
    public List<Product> test(@RequestBody Map<String, String[]> params) {
        ///РАБОТА С ПАРАМЕТРАМИ
        log.info("\nServer received params with args:");
        params.forEach((param, args) -> {
            log.info(param + ":" + Arrays.toString(args));
        });

        /// НАПОЛНЕНИЕ ТОВАРОВ findByType(requiredType)
        List<Product> dbProducts = productRepo.findByType(Type.tv);

        return dbProducts;
    }
}

