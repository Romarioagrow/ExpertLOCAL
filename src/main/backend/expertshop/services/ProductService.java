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
        orderedProduct.setPrice(product.getPrice());
        orderedProduct.setAmount(1);
        orderedProduct.setTotalPrice(product.getPrice());

        order.addProductToOrder(orderedProduct);
        orderRepo.save(order);

        System.out.println("\n");
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

    public Set<OrderedProduct> removeProductFromOrder(String id)
    {
        Order order = orderRepo.findBySessionUUID(getSessionID());
        OrderedProduct productToDelete = orderedProductRepo.findByid(Integer.parseInt(id));

        order.getOrderedProducts().remove(productToDelete);
        orderRepo.save(order);
        orderedProductRepo.delete(productToDelete);

        //log.info("Product deleted: " + productToDelete.getId() + " " + productToDelete.getProductID() + " " +productToDelete.getBrand() + " " + productToDelete.getModel());
        return order.getOrderedProducts();
    }

    public String getSessionID() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }

    public OrderedProduct changeAmount(Map<String, String> data) {

        OrderedProduct orderedProduct = orderedProductRepo.findByIdAndProductID(Integer.valueOf(data.get("orderedID")), Integer.valueOf(data.get("productID")));

        log.info(orderedProduct.toString());
        log.info("Before: "+orderedProduct.getAmount().toString());

        if (data.get("action").contains("product-less")) {
            if (orderedProduct.getAmount() > 1) orderedProduct.setAmount(orderedProduct.getAmount()-1);
        }
        else orderedProduct.setAmount(orderedProduct.getAmount()+1);

        orderedProduct.setTotalPrice(orderedProduct.getPrice()*orderedProduct.getAmount());

        orderedProductRepo.save(orderedProduct);
        log.info("After: "+orderedProduct.getAmount().toString());

        return orderedProduct;
    }
}






