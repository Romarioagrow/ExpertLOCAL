package expertshop.services;
import expertshop.domain.Product;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;

import expertshop.domain.categories.Type;

import expertshop.repos.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log
@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public List<Product> findProducts(Category category) {
        return productRepo.findByCategory(category);
    }

    public List<Product> findProducts(SubCategory subCategory) {
        return productRepo.findBySubCategory(subCategory);
    }

    public List<Product> findProducts(Type type) {
        return productRepo.findByType(type);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public List<Product> searchProducts(String searchRequest) {
        List<Product> searchedProducts = productRepo.findAll();

        //searchedProducts.forEach(product -> log.info(product.getBrand() + " " + product.getBrand().startsWith(searchRequest)));

        searchedProducts = searchedProducts.stream()
                .filter(product -> product.getBrand().startsWith(searchRequest)                                     ||
                        (product.getBrand().startsWith(searchRequest) & searchRequest.contains(product.getModel())) ||
                         product.getModel().startsWith(searchRequest)                                                ||
                        (searchRequest.contains(product.getBrand()) && searchRequest.contains(product.getModel()))  ||
                        (searchRequest.contains(product.getBrand()) && product.getModel().startsWith(searchRequest)))
                .collect(Collectors.toList());

        log.info("Products found: " + searchedProducts.size());
        return searchedProducts;
    }
}






