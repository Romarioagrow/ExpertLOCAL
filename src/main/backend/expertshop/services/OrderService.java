package expertshop.services;
import expertshop.domain.Order;
import expertshop.domain.OrderedProduct;
import expertshop.domain.Product;
import expertshop.domain.User;
import expertshop.domain.dto.OrderContacts;
import expertshop.repos.OrderRepo;
import expertshop.repos.OrderedProductRepo;
import expertshop.repos.ProductRepo;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.*;

@Log
@Service
@AllArgsConstructor
public class OrderService {
    private final MailService mailService;
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final OrderedProductRepo orderedProductRepo;

    public boolean checkUserOrder(User user) {
        return orderRepo.findByUserIDAndAcceptedFalse(user.getUserID()) != null;
    }

    private boolean checkSessionOrder() {
        return orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID()) != null;
    }

    public Order getSessionOrder() 
    { ///
        Order sessionOrder = orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID());
        return (sessionOrder != null && sessionOrder.isAccepted()) ? null : sessionOrder;
    }

    ///
    public Order getUserOrder(Long userID) {
        return orderRepo.findByUserIDAndAcceptedFalse(userID);
    }

    ///
    private Order resolveOrder(User user) {
        return user != null ? getUserOrder(user.getUserID()) : getSessionOrder();
    }

    public Set<OrderedProduct> showOrderedProducts(User user)
    {
        if (user != null && checkUserOrder(user)) {
            return orderRepo.findByUserIDAndAcceptedFalse(user.getUserID()).getOrderedProducts();
        }
        else if (checkSessionOrder()) {
            return orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID()).getOrderedProducts();
        }
        else return new HashSet<>();
    }

    public Integer addProductToOrder(String productID, User user)
    {
        Order order;
        Product product = productRepo.findByProductID(Integer.parseInt(productID));///
        OrderedProduct orderedProduct = new OrderedProduct(); ///new OrderedProduct(productID);

        if (user == null)
        {
            order = orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID());

            if (!checkSessionOrder())
            {
                order = new Order();
                order.setSessionUUID(getSessionID());
            }
            ///
            orderedProduct.constructOrderedProduct(product, productID);///
            order.addProductToOrder(orderedProduct);

            setOrderStats(order, orderedProduct.getTotalPrice());
            orderRepo.save(order);
        }
        else
        {
            order = orderRepo.findByUserIDAndAcceptedFalse(user.getUserID());

            if (order == null)
            {
                log.info("NO ORDER, NEW ONE!");
                order = new Order();
                order.setUserID(user.getUserID());
            }
            ///
            orderedProduct.constructOrderedProduct(product, productID); ///
            order.addProductToOrder(orderedProduct);

            setOrderStats(order, orderedProduct.getTotalPrice());
            orderRepo.save(order);
        }

        System.out.println("\n");
        log.info("Product with ID " + productID + " add to order");

        return order.getProductsAmount();
    }

    public Order removeProductFromOrder(User user, String id)
    {
        OrderedProduct orderedProduct = orderedProductRepo.findByid(Integer.parseInt(id));

        Order order = resolveOrder(user);
        order.getOrderedProducts().remove(orderedProduct);

        order.setTotalPrice     (order.getTotalPrice()      - orderedProduct.getTotalPrice());
        order.setTotalAmount    (order.getTotalAmount()     - orderedProduct.getAmount());
        order.setProductsAmount (order.getProductsAmount()  - 1);

        orderRepo.save(order);
        ///
        orderedProductRepo.delete(orderedProduct);
        return order;
    }

    public Queue<Object> changeAmount(User user, Map<String, String> data)
    {
        OrderedProduct orderedProduct = orderedProductRepo.findByid(Integer.valueOf(data.get("orderedID")));

        if (data.get("action").contains("product-less")) {
            if (orderedProduct.getAmount() > 1) orderedProduct.setAmount(orderedProduct.getAmount() - 1);
            else return null;
        }
        else orderedProduct.setAmount(orderedProduct.getAmount() + 1);

        orderedProduct.setTotalPrice(orderedProduct.getPrice() * orderedProduct.getAmount());
        orderedProductRepo.save(orderedProduct);

        Order order = resolveOrder(user);
        order.setTotalPrice(order.getTotalOrderPrice());
        order.setTotalAmount(order.getTotalProductsAmount());

        orderRepo.save(order);
        return packageOrderAndProduct(order, orderedProduct);
    }

    private void setOrderStats(Order order, Integer productTotalPrice)
    {
        if (order.getTotalPrice() == null)
        {
            order.setTotalPrice     (productTotalPrice);
            order.setProductsAmount (1);
            order.setTotalAmount    (1);
        }
        else
        {
            order.setTotalPrice     (order.getTotalPrice()      + productTotalPrice);
            order.setProductsAmount (order.getProductsAmount()  + 1);
            order.setTotalAmount    (order.getTotalAmount()     + 1);
        }
    }

    private Queue<Object> packageOrderAndProduct(Order order, OrderedProduct orderedProduct)
    {
        Queue<Object> orderAndProduct = new LinkedList<>();
        orderAndProduct.add(order);
        orderAndProduct.add(orderedProduct);
        return orderAndProduct;
    }

    public void confirmOrder(OrderContacts orderContacts, User user)
    {
        Order order;

        if (user == null)
        {
            order = getSessionOrder();

            order.setName   (orderContacts.getEmail());
            order.setSurname(orderContacts.getSurname());
            order.setMobile (orderContacts.getMobile());
            order.setEmail  (orderContacts.getEmail());

            acceptOrder(order);
        }
        else
        {
            order = orderRepo.findByUserIDAndAcceptedFalse(user.getUserID());

            order.setName   (user.getFirstName());
            order.setSurname(user.getLastName());
            order.setMobile (user.getMobile());
            order.setEmail  (user.getUsername());

            acceptOrder(order);
        }
    }

    private void acceptOrder(Order order)
    {
        StringBuilder orderList = new StringBuilder();

        for (OrderedProduct product : order.getOrderedProducts())
        {
            StringJoiner item = new StringJoiner (", ");
            item    .add("\n" + product.getType() + " " + product.getBrand() + " " + product.getModel())
                    .add("кол-во: " + product.getAmount().toString())
                    .add("итого \u20BD: " + product.getTotalPrice().toString())
                    .add("id товара: " + product.getProductID().toString());
            orderList.append(item.toString());
        }

        log.info(orderList.toString());
        mailService.sendOrderDetail(orderList, order);
        mailService.sendEmailToCustomer(order, orderList);

        order.setAccepted(true);
        orderRepo.save(order);
    }

    public Set<Order> showUserOrders(Long userID) {
        return orderRepo.findOrdersByUserID(userID);
    }

    public String getSessionID() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }
}
