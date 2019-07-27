package expertshop.services;
import expertshop.domain.Order;
import expertshop.domain.OrderedProduct;
import expertshop.domain.Product;
import expertshop.domain.User;
import expertshop.domain.dto.OrderContacts;
import expertshop.repos.OrderRepo;
import expertshop.repos.OrderedRepo;
import expertshop.repos.ProductRepo;

import expertshop.repos.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.*;

@Log
@Service
@AllArgsConstructor
public class OrderService {
    private final MailService   mailService;
    private final OrderRepo     orderRepo;
    private final ProductRepo   productRepo;
    private final OrderedRepo   orderedRepo;
    private final UserRepo      userRepo;

    public LinkedList<Integer> addProductToOrder(String productID, User user)
    {
        Order order;
        OrderedProduct orderedProduct = new OrderedProduct();
        Product product = productRepo.findByProductID(productID);
        int discount = 0;

        if (user == null)
        {
            order = orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID());

            if (!checkSessionOrder())
            {
                order = new Order();
                order.setSessionUUID(getSessionID());
                order.setTotalBonus(product.getBonus());
            }
            ///
            orderedProduct.constructOrderedProduct(product);///
            order.addProductToOrder(orderedProduct);
            order.setTotalBonus(order.extractTotalBonus());

            setOrderStats(order, orderedProduct.getTotalPrice());
            orderRepo.save(order);
        }
        else
        {
            order = orderRepo.findByUserIDAndAcceptedFalse(user.getUserID());

            if (order == null)
            {
                //log.info("NO ORDER, NEW ONE!");
                order = new Order();
                order.setUserID(user.getUserID());
                order.setTotalBonus(product.getBonus());
            }

            if (order.getDiscountApplied()) {
                order = dropDiscountParams(order);
            }

            orderedProduct.constructOrderedProduct(product); ///

            order.addProductToOrder(orderedProduct);
            order.setTotalBonus(order.getTotalBonus() + product.getBonus());
            setOrderStats(order, orderedProduct.getTotalPrice());

            orderRepo.save(order);
            discount = calculateDiscount(user, order);
        }

        //System.out.println("\n");
        //log.info("Product with ID " + productID + " add to getOrder");
        //log.info("Product bonus: " + product.getBonus());

        return payload(order.getProductsAmount(), discount);
    }

    public LinkedList<Object> changeAmount(User user, Map<String, String> data)
    {
        OrderedProduct orderedProduct = orderedRepo.findByOrderedID(Long.valueOf(data.get("orderedID")));
        int discount = 0;

        if (data.get("action").contains("product-less")) {
            if (orderedProduct.getOrderedAmount() > 1) {
                orderedProduct.setOrderedAmount(orderedProduct.getOrderedAmount() - 1);
            }
            else return null;
        }
        else {
            orderedProduct.setOrderedAmount(orderedProduct.getOrderedAmount() + 1);
        }

        orderedProduct.setTotalPrice(orderedProduct.getProductPrice() * orderedProduct.getOrderedAmount());
        orderedRepo.save(orderedProduct);

        Order order = resolveOrder(user);
        order.setTotalPrice (order.extractTotalOrderPrice());
        order.setTotalAmount(order.extractTotalProductsAmount());
        order.setTotalBonus (order.extractTotalBonus());
        orderRepo.save(order);

        if (user != null) discount = calculateDiscount(user, order);

        //log.info("Order total price: " + order.getTotalPrice());
        return payload(order, orderedProduct, discount);
    }

    public LinkedList<Object> removeProductFromOrder(User user, String orderedID)
    {
        OrderedProduct orderedProduct = orderedRepo.findByOrderedID(Long.parseLong(orderedID));
        Order order = resolveOrder(user);
        int discount = 0;

        order.getOrderedProducts().remove(orderedProduct);
        order.setTotalPrice     (order.getTotalPrice()      - orderedProduct.getTotalPrice());
        order.setProductsAmount (order.getOrderedProducts().size());
        order.setTotalAmount    (order.extractTotalProductsAmount());
        order.setTotalBonus     (order.extractTotalBonus());
        orderRepo.save(order);
        orderedRepo.delete(orderedProduct);

        if (user != null) discount = calculateDiscount(user, order);
        return payload(order, discount);
        //return order;
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

    public int calculateDiscount(User user, Order order) {
        int discount = 0;
        try {
            if (user.getBonus() != null)
            {
                int discountBonus = user.getBonus();
                if (discountBonus == 0)
                {
                    return discount;
                }
                else if (order != null && order.getTotalPrice() != 0)
                {
                    int totalPrice = order.getTotalPrice();
                    discount = 100 * discountBonus / totalPrice;

                    if (discount >= 20) discount = 20;
                    else if (discount == 0) discount = 1;

                    log.info("Discount % " + discount);
                    return discount;
                }
            }
        }
        catch (NullPointerException e) {
            log.warning(e.toString());
        }
        return discount;
    }

    public LinkedList<Object> applyDiscount(Map<String, String> discountData, User user) {
        log.info(discountData.toString());

        Order order = orderRepo.findByOrderID(Long.parseLong(discountData.get("orderID")));
        int bonusOff = Integer.parseInt(discountData.get("bonus"));
        int discountPercent = Integer.parseInt(discountData.get("discount"));

        if (discountPercent == 0) {
            return null;
        }
        else if (discountPercent < 20)
        {
            order.setDiscountPrice(order.getTotalPrice() - bonusOff);
            order.setTotalDiscount(discountPercent);
            order.setBonusOff(bonusOff);
        }
        else if (discountPercent == 20)
        {
            int bonusOffPart = order.getTotalPrice() * discountPercent / 100;
            order.setDiscountPrice(order.getTotalPrice() - bonusOffPart);
            order.setTotalDiscount(bonusOffPart);
            order.setBonusOff(bonusOffPart);
            //user.setBonus(user.getBonus() - bonusOffPart);
        }
        order.setDiscountPercent(discountPercent);
        order.setDiscountApplied(true);

        userRepo.save(user);
        orderRepo.save(order);
        return payload(order, user);
    }

    public LinkedList<Object> dropDiscount(String orderID, User user) {
        Order order = orderRepo.findByOrderID(Long.valueOf(orderID));

        if (order.getDiscountApplied())
        {
            //order = dropDiscountParams(order);
            return payload(dropDiscountParams(order), user);
        }
        return null;
    }
    Order dropDiscountParams(Order order) {
        order.setDiscountApplied  (false);
        order.setDiscountPrice    (0);
        order.setTotalDiscount    (0);
        order.setDiscountPercent  (0);
        order.setBonusOff         (0);
        orderRepo.save(order);
        return order;
    }

    public Object confirmOrder(OrderContacts orderContacts, User user)
    {
        log.info(orderContacts.toString());

        Order order;
        if (user == null)
        {
            order = getSessionOrder();
            order.setName    (orderContacts.getFirstName());
            order.setSurname (orderContacts.getLastName());
            order.setOtchestvo(orderContacts.getOtchestvo());
            order.setMobile  (orderContacts.getUsername());
            order.setEmail   (orderContacts.getEmail());
            order.setShortTel(orderContacts.getUsername().replaceAll("\\W", ""));
        }
        else
        {
            order = orderRepo.findByUserIDAndAcceptedFalse(user.getUserID());
            order.setName    (user.getFirstName());
            order.setSurname (user.getLastName());
            order.setOtchestvo(user.getOtchestvo());
            order.setMobile  (user.getEmail());
            order.setEmail   (user.getUsername());
            order.setShortTel(user.getUsername().replaceAll("\\W", ""));

            int bonusOff;
            if (order.getBonusOff() == null) bonusOff = 0;
            else bonusOff = order.getBonusOff();

            user.setBonus(user.getBonus() - bonusOff);
            user.setBonus(user.getBonus() + order.getTotalBonus());
        }

        if (orderContacts.getCity() != null) {
            order.setCity(orderContacts.getCity());
            order.setStreet(orderContacts.getStreet());
            order.setHouse(orderContacts.getHouse());
            order.setApartment(orderContacts.getApartment());
        }
        log.info(order.toString());

        orderRepo.save(order);
        acceptOrder(order);
        return order;
        /*LinkedList<Object> payload = new LinkedList<>();
        payload.add(null);
        payload.add(order);
        return payload;*/
    }
    private void acceptOrder(Order order)
    {
        StringBuilder orderList = new StringBuilder();

        for (OrderedProduct product : order.getOrderedProducts())
        {
            StringJoiner item = new StringJoiner (", ");
            item    .add("\n" + product.getProductType() + " " + product.getProductName())
                    .add("кол-во: "             + product.getOrderedAmount().toString())
                    .add("итого \u20BD: "       + product.getTotalPrice().toString())
                    .add("orderedID товара: "   + product.getProductID());
            orderList.append(item.toString());
        }

        //log.info(orderList.toString());
        mailService.sendOrderDetail(orderList, order);
        //mailService.sendEmailToCustomer(order, orderList);

        order.setAccepted(true);
        orderRepo.save(order);
        //log.fine("Order " + order.getOrderID() + " is accepted!");
    }

    private LinkedList<Integer> payload(Integer productsAmount, int discount) {
        LinkedList<Integer> payload = new LinkedList<>();
        payload.add(productsAmount);
        payload.add(discount);
        return payload;
    }
    private LinkedList<Object> payload(Order order, OrderedProduct orderedProduct, int discount) {
        LinkedList<Object> payload = new LinkedList<>();
        payload.add(order);
        payload.add(orderedProduct);
        payload.add(discount);
        return payload;
    }
    private LinkedList<Object> payload(Order order, Object discount) {
        LinkedList<Object> payload = new LinkedList<>();
        payload.add(order);
        payload.add(discount);
        return payload;
    }

    public Set<Order> showUserOrders(Long userID) {
        return orderRepo.findOrdersByUserIDAndAcceptedTrue(userID);
    }

    public String getSessionID() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }

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
    public Order resolveOrder(User user) {
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

    public List<Order> showAcceptedOrders(String request) {
        if (request.isEmpty()) return orderRepo.findAllByAcceptedTrue();

        Order order;
        request = request.replaceAll("\\W", "");
        order = orderRepo.findByOrderID(Long.parseLong(request));
        if (order != null) return Collections.singletonList(order);

        List<Order> orders = orderRepo.findByShortTel(request);
        if (!orders.isEmpty()) return orders;

        return null;
    }
}
