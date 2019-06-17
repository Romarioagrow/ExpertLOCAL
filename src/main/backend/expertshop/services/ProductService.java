package expertshop.services;
import expertshop.domain.Order;
import expertshop.domain.OrderedProduct;
import expertshop.domain.Product;
import expertshop.domain.User;
import expertshop.repos.OrderRepo;
import expertshop.repos.ProductRepo;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;
import java.util.stream.Collectors;

import static expertshop.controllers.ControllerService.getSessionID;

@Log
@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public List<Product> findProducts(String requiredProduct)
    {
        log.info(requiredProduct);

        Set<Product> products = productRepo.findByProductGroupContainingIgnoreCase(requiredProduct);
        products.addAll(productRepo.findByTypeContainingIgnoreCase(requiredProduct));

        List<Product> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparing(Product::getSupplier));

        products.forEach(productRBT -> log.info(productRBT.toString()));
        log.info("Products found: " + products.size());
        return sortedProducts;
    }

    public List<Product> searchProducts(String searchRequest)
    {
        log.info("Search request: " + searchRequest);

        List<Product> searchedProducts = productRepo.findAll().stream()
                .filter(product -> product.getFullName()/*getBrand().concat(" ").concat(product.getModel())*/.contains(searchRequest))
                .collect(Collectors.toList());
        log.info("Products found: " + searchedProducts.size());

        return searchedProducts;
    }

    public void getOrderedID(User user, Model model)
    {
        if (user != null && orderRepo.findByUserIDAndAcceptedFalse(user.getUserID()) != null)
        {
            Order order = orderRepo.findByUserIDAndAcceptedFalse(user.getUserID());
            collectID(order, model);
        }
        else if (orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID()) != null)
        {
            Order order = orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID());
            collectID(order, model);
        }
        else log.info("getOrder empty");
    }

    void collectID(Order order, Model model) {
        Set<String> orderedProductsID = new HashSet<>();

        for (OrderedProduct product : order.getOrderedProducts())
            orderedProductsID.add(product.getProductID().toString());

        model.addAttribute("orderedProductsID", orderedProductsID);
    }
}







