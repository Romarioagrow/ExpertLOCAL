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
import java.util.stream.Stream;

@Log
@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    //String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();

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

    public void addProductToOrder(/*Order order, */String productID)
    {
        ///ЕСЛИ НЕТ ТАКОЙ ЗАПИСИ БД ПО ID СЕССИИ/ЮЗЕРА ТО СОЗДАТЬ ЗАПИСЬ В БД ПО ID СЕССИИ/ЮЗЕРА
        ///ЕСЛИ УЖЕ СУЩЕСТВУЕТ ТО НАЙТИ НУЖНЫЕ ПРОДУКТ ПО ID И ДОБАВИТЬ В БАЗУ

        log.info("Received product with ID " + productID);
        String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
        log.info(sessionID);

        Order order = orderRepo.findBySessionUUID(sessionID);
        if (order == null)
        {
            order = new Order();
            order.setSessionUUID(sessionID);
        }
        //Product product = productRepo.findByProductID(Integer.parseInt(productID));
        order.addProductToOrder(Integer.parseInt(productID), 1);

        orderRepo.save(order);

        //log.info("Product with ID" + productID + " add to order");
    }

    public Map<Product, Integer> showOrderedProducts()
    {
        String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
        Order order = orderRepo.findBySessionUUID(sessionID);
        Map<Integer, Integer> products = order.getOrderedProducts();
        Map<Product, Integer> productsToOrder = new LinkedHashMap<>();

        products.forEach((productID, amount) -> log.info(productID.toString() + " " + amount.toString()));
        products.forEach((key, value) -> productsToOrder.put(productRepo.findByProductID(key), value));

        //products = products.keySet().stream().map(integer -> products.put(productRepo.findByProductID(integer)))
        productsToOrder.forEach((product, integer) -> log.info(product.getBrand()+ " " + product.getModel()));

        //productsToOrder


        //productsToOrder.entrySet().stream().sorted(Map.Entry.comparingByValue());

        return productsToOrder;
    }
}






