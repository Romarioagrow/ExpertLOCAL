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

import java.util.List;
import java.util.Random;
import java.util.UUID;
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
        // List<Product> orderedProducts;

        log.info("Received product with ID " + productID);
        String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
        log.info(sessionID);

        /*Order order = orderRepo.findBySessionUUID(sessionID);
        if (order == null)
        {
            order = new Order();
            order.setSessionUUID(sessionID);
        }
        order.addProductToOrder(Integer.parseInt(productID), 1);
        log.info("Product with ID" + productID + " add to order");
        orderRepo.save(order);*/

        Order order = orderRepo.findBySessionUUID(sessionID);
        if (order == null)
        {
            order = new Order();
            order.setSessionUUID(sessionID);
        }
        Product product = productRepo.findByProductID(Integer.parseInt(productID));
        order.addProductToOrder(product);
        log.info("Product with ID" + productID + " add to order");
        orderRepo.save(order);




        /*if (orderRepo.findBySessionUUID(sessionID) == null) {
            order.setSessionUUID(sessionID);
            //order.setOrderedProducts(productRepo.findByProductID(Integer.parseInt(productID)), (Integer) 1);//.put(productRepo.findByProductID(Integer.parseInt(productID)), (Integer) 1);
            order.addProductToOrder(Integer.parseInt(productID), 1);
            orderRepo.save(order);
        }
        else {
            Order existOrder = orderRepo.findBySessionUUID(sessionID);
            order.addProductToOrder(Integer.parseInt(productID), 1);
            orderRepo.save(order);
        }
*/
        /*Order order = orderRepo.findBySessionUUID(sessionID);
        order.setSessionUUID(sessionID);
        orderRepo.save(order);*/

        /*Order order = new Order();
        order.setSessionUUID(sessionID);
        orderRepo.save(order);*/

        //order.setOrderID(UUID.randomUUID());
        /*if (orderRepo.findBySessionUUID(sessionID).getSessionUUID() == null) {
            order.setSessionUUID(sessionID);
            //order.getOrderedProducts().put(productRepo.findByProductID(Integer.parseInt(productID)), (Integer) 1);

            orderRepo.save(order);
            //order.setOrderedProducts(productRepo.findByProductID(Integer.parseInt(productID)), amount);
        }*/



    }

    public List<Product> showOrderedProducts()
    {

        return null;
    }
}






