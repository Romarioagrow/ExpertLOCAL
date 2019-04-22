package expertshop.controllers;
import expertshop.domain.Product;
import expertshop.services.FilterService;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import java.util.*;

@Log
@RestController
@AllArgsConstructor
public class FilterController {
    private final FilterService filterService;

    @PostMapping("/{req_type}")
    public List<Product> filterProducts(@RequestBody Map<String, Object> params, @PathVariable String req_type) {
        return filterService.filterProducts(params, req_type);
    }
}
