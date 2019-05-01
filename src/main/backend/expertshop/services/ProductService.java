package expertshop.services;

import expertshop.domain.Product;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.domain.categories.Type;
import expertshop.repos.ProductRepo;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

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

    public List<Product> searchProducts(String searchRequest)
    {
        log.info("Search request: " + searchRequest);
        List<Product> searchedProducts = productRepo.findAll().stream()
                .filter(product -> product.getBrand().concat(" ").concat(product.getModel()).contains(searchRequest))
                .collect(Collectors.toList());

        log.info("Products found: " + searchedProducts.size());
        return searchedProducts;
    }

    public void addProductToOrder(String productID)
    {
        ///ЕСЛИ НЕТ ТАКОЙ ЗАПИСИ БД ПО ID СЕССИИ/ЮЗЕРА ТО СОЗДАТЬ ЗАПИСЬ В БД ПО ID СЕССИИ/ЮЗЕРА
        ///ЕСЛИ УЖЕ СУЩЕСТВУЕТ ТО НАЙТИ НУЖНЫЕ ПРОДУКТ ПО ID И ДОБАВИТЬ В БАЗУ
        log.info("Received product with ID " + productID);

        List<Product> orderedProducts;

        String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
        log.info(sessionID);
    }

    public List<Product> showOrderedProducts()
    {

        return null;
    }
}






