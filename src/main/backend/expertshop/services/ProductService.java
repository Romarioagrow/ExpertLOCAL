package expertshop.services;

import expertshop.domain.Order;
import expertshop.domain.OrderedProduct;
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

        /*
        Product product = productRepo.findByProductID(Integer.parseInt(productID));
        OrderedProduct ordProduct = new OrderedProduct();
        ordProduct.setProductID(productID);
        ordProduct.setBrand(product.getBrand());
        ordProduct.setModel(product.getModel());
        ordProduct.setType(product.getProductParams().getType());
        ordProduct.setAmount(1);
        ordProduct.setTotalPrice(ordProduct.getPrice());
        order.addProductToOrder(orderedProduct);
        */

        order.addProductToOrder(Integer.parseInt(productID), 1);
        orderRepo.save(order);

        log.info("Product with ID" + productID + " add to order");
    }

    ///Set<OrderedProduct> ||
    ///Map<OrderedProduct, ProductParams>
    public Map<Product, Integer> showOrderedProducts()
    {
        if (orderRepo.findBySessionUUID(getSessionID()) != null)
        {
            Order order = orderRepo.findBySessionUUID(getSessionID());

            //Map<Integer, Product> products = collectOrderedProducts();
            ///В МЕТОД!
            Map<Integer, Integer> productsDB = order.getOrderedProducts();
            Map<Product, Integer> products = new LinkedHashMap<>();


            /*СЕРИЛИЗОВАТЬ ОБА ОБЪЕКТА В STRING/JSON И ПОЛОЖИТЬ В MAP*/
            /*ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(object);*/

            /*Set<OrderedProduct> prd1;
            Map<OrderedProduct, ProductParams> prd2;
            productsDB.forEach((productID, amount) -> prd2.put(orderedProductRepo.findByProductID(productID), productParamsRepo.findByProductID(productID)));*/

            productsDB.forEach((productID, amount) -> products.put(productRepo.findByProductID(productID), amount));
            //products.forEach((product, integer) -> log.info(product.getBrand()+ " " + product.getModel()));

            return products;
            //return collectOrderedProducts();
        }
        return null;
    }

    public Map<Product, Integer> removeProductFromOrder(String productID)
    {
        Order order = orderRepo.findBySessionUUID(getSessionID());

        order.removeProductFromOrder(Integer.parseInt(productID));
        orderRepo.save(order);

        //Map<Integer, Product> products = collectOrderedProducts();
        ///В МЕТОД!
        Map<Integer, Integer> productsDB = order.getOrderedProducts();
        Map<Product, Integer> products = new LinkedHashMap<>();

        productsDB.forEach((product_ID, amount) -> products.put(productRepo.findByProductID(product_ID), amount));

        return products;
        //return collectOrderedProducts();
    }

    public String getSessionID() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }
}






