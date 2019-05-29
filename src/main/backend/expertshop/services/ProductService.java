package expertshop.services;

import expertshop.domain.Order;
import expertshop.domain.OrderedProduct;
import expertshop.domain.Product;
import expertshop.domain.User;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.domain.categories.Type;
import expertshop.repos.OrderRepo;
import expertshop.repos.OrderedProductRepo;
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
    private final OrderedProductRepo orderedProductRepo;
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

    public boolean checkUserOrder(User user) {
        return orderRepo.findByUserIDAndAcceptedFalse(user.getUserID()) != null;
    }

    public Set<OrderedProduct> showOrderedProducts(User user)
    {
        if (user != null) {
            if (checkUserOrder(user))
                return orderRepo.findByUserIDAndAcceptedFalse(user.getUserID()).getOrderedProducts();
            else return new HashSet<>();
        }
        else if (orderRepo.findBySessionUUID(getSessionID()) != null) {
            return orderRepo.findBySessionUUID(getSessionID()).getOrderedProducts();
        }
        return null;
    }
    /*public Object*//*Optional<Set<OrderedProduct>>*//**//*Set<OrderedProduct>*//* showOrderedProducts(User user)
    {
        if (user != null) {
            return Optional.ofNullable(orderRepo.findByUserIDAndAcceptedFalse(user.getUserID()).getOrderedProducts());*//*orderRepo.findByUserIDAndAcceptedFalse(user.getUserID()).getOrderedProducts();*//*
        }
        else if (orderRepo.findBySessionUUID(getSessionID()) != null) {
            return orderRepo.findBySessionUUID(getSessionID()).getOrderedProducts();
        }
        return null;
    }*/

    public void addProductToOrder(String productID, User user)
    {
        Order order;
        Product product = productRepo.findByProductID(Integer.parseInt(productID));
        OrderedProduct orderedProduct = new OrderedProduct();

        if (user == null)
        {
            order = orderRepo.findBySessionUUID(getSessionID());

            if (order == null)
            {
                order = new Order();
                order.setSessionUUID(getSessionID());
            }

            orderedProduct.constructOrderedProduct(product, productID);
            order.addProductToOrder(orderedProduct);

            setOrderStats(order, orderedProduct.getTotalPrice());
            orderRepo.save(order);
        }
        else
        {
            order = orderRepo.findByUserIDAndAcceptedFalse(user.getUserID());

            if (order == null /*|| order.isAccepted()*/)
            {
                log.info("NO ORDER, NEW ONE!");
                order = new Order();
                order.setUserID(user.getUserID());
            }

            orderedProduct.constructOrderedProduct(product, productID);
            order.addProductToOrder(orderedProduct);

            setOrderStats(order, orderedProduct.getTotalPrice());
            orderRepo.save(order);
        }

        System.out.println("\n");
        log.info("Product with ID " + productID + " add to order");
    }
    private void setOrderStats(Order order, Integer productTotalPrice)
    {
        if (order.getTotalPrice() == null)
        {
            order.setTotalPrice     (productTotalPrice);
            order.setProductsAmount (1);
            order.setTotalAmount    (1);
        } else {
            order.setTotalPrice     (order.getTotalPrice()      + productTotalPrice);
            order.setProductsAmount (order.getProductsAmount()  + 1);
            order.setTotalAmount    (order.getTotalAmount()     + 1);
        }
    }

    public Order removeProductFromOrder(String id)
    {
        Order order = orderRepo.findBySessionUUID(getSessionID()); /// orderService.getOrder() проверят по sessionID или по userID
        OrderedProduct orderedProduct = orderedProductRepo.findByid(Integer.parseInt(id));

        order.getOrderedProducts().remove(orderedProduct);
        order.setTotalPrice     (order.getTotalPrice()      - orderedProduct.getTotalPrice());
        order.setTotalAmount    (order.getTotalAmount()     - orderedProduct.getAmount());
        order.setProductsAmount (order.getProductsAmount()  - 1);

        orderRepo.save(order);
        orderedProductRepo.delete(orderedProduct);

        return order;
    }

    public Queue<Object> changeAmount(Map<String, String> data)
    {
        OrderedProduct orderedProduct = orderedProductRepo.findByid(Integer.valueOf(data.get("orderedID")));

        if (data.get("action").contains("product-less")) {
            if (orderedProduct.getAmount() > 1) orderedProduct.setAmount(orderedProduct.getAmount() - 1);
            else return null;
        }
        else orderedProduct.setAmount(orderedProduct.getAmount() + 1);

        orderedProduct.setTotalPrice(orderedProduct.getPrice() * orderedProduct.getAmount());
        orderedProductRepo.save(orderedProduct);

        Order order = orderRepo.findBySessionUUID(getSessionID()); ////F orderService.getOrder() проверят по sessionID или по userID
        order.setTotalPrice(order.getTotalOrderPrice());
        order.setTotalAmount(order.getTotalProductsAmount());
        orderRepo.save(order);

        return packageOrderAndProduct(order, orderedProduct);
    }
    private Queue<Object> packageOrderAndProduct(Order order, OrderedProduct orderedProduct)
    {
        Queue<Object> orderAndProduct = new LinkedList<>();
        orderAndProduct.add(order);
        orderAndProduct.add(orderedProduct);
        return orderAndProduct;
    }

    public String getSessionID() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }
}






