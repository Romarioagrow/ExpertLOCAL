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

    public void addProductToOrder(String productID)
    {
        ///ЕСЛИ НЕТ ТАКОЙ ЗАПИСИ БД ПО ID СЕССИИ/ЮЗЕРА ТО СОЗДАТЬ ЗАПИСЬ В БД ПО ID СЕССИИ/ЮЗЕРА
        ///ЕСЛИ УЖЕ СУЩЕСТВУЕТ ТО НАЙТИ НУЖНЫЕ ПРОДУКТ ПО ID И ДОБАВИТЬ В БАЗУ
        /*String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId(); ///GET_SESSION!!!
        log.info(sessionID);*/

        log.info("Received product with ID " + productID);

        Order order = orderRepo.findBySessionUUID(getSessionID());
        if (order == null)
        {
            order = new Order();
            order.setSessionUUID(getSessionID());
        }
        //Product product = productRepo.findByProductID(Integer.parseInt(productID));
        order.addProductToOrder(Integer.parseInt(productID), 1);

        orderRepo.save(order);

        //log.info("Product with ID" + productID + " add to order");
    }

    public Map<Product, Integer> showOrderedProducts()
    {
        //String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();

        if (orderRepo.findBySessionUUID(getSessionID()) != null)
        {
            Order order = orderRepo.findBySessionUUID(getSessionID());

            Map<Integer, Integer> products = order.getOrderedProducts();
            Map<Product, Integer> productsToOrder = new LinkedHashMap<>();

            //products.forEach((productID, amount) -> log.info(productID.toString() + " " + amount.toString()));
            products.forEach((productID, amount) -> productsToOrder.put(productRepo.findByProductID(productID), amount));

            //products = products.keySet().stream().map(integer -> products.put(productRepo.findByProductID(integer)))
            productsToOrder.forEach((product, integer) -> log.info(product.getBrand()+ " " + product.getModel()));

            //productsToOrder

            //productsToOrder.entrySet().stream().sorted(Map.Entry.comparingByValue());

            return productsToOrder;
        }
        return null;
    }

    public Map<Product, Integer> removeProductFromOrder(String productID)
    {
        Order order = orderRepo.findBySessionUUID(getSessionID());

        order.removeProductFromOrder(Integer.parseInt(productID));

        Map<Integer, Integer> productsDB = order.getOrderedProducts();
        Map<Product, Integer> products = new LinkedHashMap<>();

        productsDB.forEach((product_ID, amount) -> products.put(productRepo.findByProductID(product_ID), amount));

        //order
        /*Map<Integer, Integer> products = order.getOrderedProducts();
        Map<Product, Integer> productsToOrder = new LinkedHashMap<>();*/

        /*products.entrySet().stream()
                .map(productDB -> productsToOrder.put(productRepo.findByProductID(productDB.getKey()), productDB.getValue()))
                .
*/
        return products;
    }

    public String getSessionID() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }
}






