package expertshop.services;

import expertshop.domain.Order;
import expertshop.domain.OrderedProduct;
import expertshop.domain.Product;
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
    private final MailService mailService;

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

    public Set<OrderedProduct> showOrderedProducts()
    {
        if (orderRepo.findBySessionUUID(getSessionID()) != null) {
            return orderRepo.findBySessionUUID(getSessionID()).getOrderedProducts();
        }
        return null;
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

        Product product = productRepo.findByProductID(Integer.parseInt(productID));

        OrderedProduct orderedProduct = new OrderedProduct();
        orderedProduct.setProductID(Integer.parseInt(productID));
        orderedProduct.setAmount(1);
        orderedProduct.setBrand(product.getBrand());
        orderedProduct.setModel(product.getModel());
        orderedProduct.setType(product.getProductParams().getType());
        orderedProduct.setPic(product.getProductParams().getPic());
        orderedProduct.setPrice(product.getPrice());
        orderedProduct.setTotalPrice(product.getPrice());

        order.addProductToOrder(orderedProduct);

        if (order.getTotalPrice() == null) {
            order.setTotalPrice(orderedProduct.getTotalPrice());
            order.setProductsAmount(1);
            order.setTotalAmount(1);
        } else {
            order.setTotalPrice(order.getTotalPrice() + orderedProduct.getTotalPrice());
            order.setProductsAmount(order.getProductsAmount() + 1);
            order.setTotalAmount(order.getTotalAmount() + 1);
        }

        orderRepo.save(order);

        System.out.println("\n");
        log.info("Product with ID " + productID + " add to order");
    }

    public Order removeProductFromOrder(String id)
    {
        Order order = orderRepo.findBySessionUUID(getSessionID());
        OrderedProduct orderedProduct = orderedProductRepo.findByid(Integer.parseInt(id));

        order.getOrderedProducts().remove(orderedProduct);
        order.setTotalPrice     (order.getTotalPrice() - orderedProduct.getTotalPrice());
        order.setTotalAmount    (order.getTotalAmount() - orderedProduct.getAmount());
        order.setProductsAmount (order.getProductsAmount() - 1);

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
    private Queue<Object> packageOrderAndProduct(Order order, OrderedProduct orderedProduct) {
        Queue<Object> orderAndProduct = new LinkedList<>();
        orderAndProduct.add(order);
        orderAndProduct.add(orderedProduct);
        return orderAndProduct;
    }

    public void confirmOrder(Map<String, String> contacts) {
        Order order = orderRepo.findByOrderID(Integer.parseInt(contacts.get("orderID")));
        order.setName(contacts.get("name"));
        order.setSurname(contacts.get("surname"));
        order.setMobile(contacts.get("mobile"));
        order.setEmail(contacts.get("email"));

        Set<OrderedProduct> orderedProducts = order.getOrderedProducts();
        StringBuilder orderList = new StringBuilder();

        for (OrderedProduct product : orderedProducts) {
            StringJoiner  item = new StringJoiner (", ");
            item    .add("\n" + product.getType() + " " + product.getBrand() + " " + product.getModel())
                    .add("кол-во: " + product.getAmount().toString())
                    .add("итого \u20BD: " + product.getTotalPrice().toString())
                    .add("id товара: " + product.getProductID().toString());
            orderList.append(item.toString());
        }

        log.info(orderList.toString());
        mailService.sendOrderDetail(orderList, order.getOrderID());
        mailService.sendEmailToCustomer(order, orderList);

        order.setAccepted(true);
        orderRepo.save(order);
    }

    public String getSessionID() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }
}






