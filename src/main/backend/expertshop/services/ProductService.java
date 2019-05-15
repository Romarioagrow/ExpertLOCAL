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

        Product product = productRepo.findByProductID(Integer.parseInt(productID));

        OrderedProduct orderedProduct = new OrderedProduct();
        orderedProduct.setProductID(Integer.parseInt(productID));
        orderedProduct.setBrand(product.getBrand());
        orderedProduct.setModel(product.getModel());
        orderedProduct.setType(product.getProductParams().getType());
        orderedProduct.setPic(product.getProductParams().getPic());
        orderedProduct.setAmount(1);
        orderedProduct.setTotalPrice(product.getPrice());

        order.addProductToOrder(orderedProduct);
        orderRepo.save(order);

        log.info("Product with ID" + productID + " add to order");
    }

    public Set<OrderedProduct> showOrderedProducts()
    {
        if (orderRepo.findBySessionUUID(getSessionID()) != null)
        {
            Order order = orderRepo.findBySessionUUID(getSessionID());
            return order.getOrderedProducts();
        }
        return null;
    }

    public Set<OrderedProduct> removeProductFromOrder(String productID)
    {
        Order order = orderRepo.findBySessionUUID(getSessionID());

        order.setOrderedProducts(order.getOrderedProducts().stream()
                .filter(orderedProduct -> !orderedProduct.getProductID().equals(Integer.parseInt(productID))).collect(Collectors.toSet()));
        orderRepo.save(order);
        //orderedProductRepo.delete(orderedProductRepo.findByProductID(Integer.parseInt(productID)));

        return order.getOrderedProducts();
    }

    public String getSessionID() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }

    public Integer changeAmount(Map<String, String> data) {

        data.forEach((s, s2) -> log.info(s + " " + s2));

        Integer ID = Integer.valueOf(data.get("orderedID"));
        Integer productID = Integer.valueOf(data.get("productID"));

        OrderedProduct orderedProduct = orderedProductRepo.findByIdAndProductID(ID, productID);
        orderedProduct.setAmount(orderedProduct.getAmount()+1);
        orderedProductRepo.save(orderedProduct);

        //Order order = orderRepo.findBySessionUUID(getSessionID());
        log.info(orderedProduct.getAmount().toString());

        return orderedProduct.getAmount();
    }
}






