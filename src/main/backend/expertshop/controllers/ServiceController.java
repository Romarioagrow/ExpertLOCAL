package expertshop.controllers;
import expertshop.domain.OrderedProduct;
import expertshop.domain.Product;
import expertshop.services.FilterService;
import expertshop.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Log
@RestController
@AllArgsConstructor
public class ServiceController {
    private final FilterService filterService;
    private final ProductService productService;

    @PostMapping("/{req_type}")
    public List<Product> filterProducts(@RequestBody Map<String, Object> params, @PathVariable String req_type) {
        return filterService.filterProducts(params, req_type);
    }

    @PostMapping("/search")
    public List<Product> searchProducts(@RequestBody String searchRequest) {
        return productService.searchProducts(searchRequest);
    }

    @PostMapping("/order")
    private void addProductToOrder(@RequestBody String productID) {
        productService.addProductToOrder(productID);
    }

    @DeleteMapping("/order")
    private Set<OrderedProduct> removeProductFromOrder(@RequestBody String productID) {
        return productService.removeProductFromOrder(productID);
    }

    @PutMapping("/order")
    private OrderedProduct changeAmount(@RequestBody Map<String, String> data) {
        return productService.changeAmount(data);
    }
}
