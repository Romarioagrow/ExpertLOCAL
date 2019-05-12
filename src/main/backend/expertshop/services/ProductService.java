package expertshop.services;

import expertshop.domain.Order;
import expertshop.domain.Product;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.domain.categories.Type;
import expertshop.repos.OrderRepo;
import expertshop.repos.ProductRepo;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.*;
import java.util.stream.Collectors;

@Log
@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

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
        log.info("Search request: " + searchRequest);
        List<Product> searchedProducts = productRepo.findAll().stream()
                .filter(product -> product.getBrand().concat(" ").concat(product.getModel()).contains(searchRequest))
                .collect(Collectors.toList());

        log.info("Products found: " + searchedProducts.size());
        return searchedProducts;
    }

    public void addProductToOrder(String productID)
    {
        log.info("Received product with ID " + productID);

        Order order = orderRepo.findBySessionUUID(getSessionID());
        if (order == null)
        {
            order = new Order();
            order.setSessionUUID(getSessionID());
        }

        order.addProductToOrder(Integer.parseInt(productID), 1);
        orderRepo.save(order);

        log.info("Product with ID" + productID + " add to order");
    }

    public Map<Product, Integer> showOrderedProducts()
    {
        if (orderRepo.findBySessionUUID(getSessionID()) != null)
        {
            Order order = orderRepo.findBySessionUUID(getSessionID());

            //Map<Integer, Product> products = collectOrderedProducts();
            ///В МЕТОД!
            Map<Integer, Integer> productsDB = order.getOrderedProducts();
            Map<Product, Integer> products = new LinkedHashMap<>();

            productsDB.forEach((productID, amount) -> products.put(productRepo.findByProductID(productID), amount));
            //products.forEach((product, integer) -> log.info(product.getBrand()+ " " + product.getModel()));

            return products;
            //return collectOrderedProducts();
        }
        return null;
    }

    public Map<Integer, Product> removeProductFromOrder(String productID)
    {
        Order order = orderRepo.findBySessionUUID(getSessionID());

        order.removeProductFromOrder(Integer.parseInt(productID));
        orderRepo.save(order);

        //Map<Integer, Product> products = collectOrderedProducts();
        ///В МЕТОД!
        Map<Integer, Integer> productsDB = order.getOrderedProducts();
        Map<Integer, Product> products = new LinkedHashMap<>();

        productsDB.forEach((product_ID, amount) -> products.put(amount, productRepo.findByProductID(product_ID)));

        return products;
        //return collectOrderedProducts();
    }

    public String getSessionID() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }
}






