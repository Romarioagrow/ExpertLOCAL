package expertshop.services;
import expertshop.domain.Order;
import expertshop.domain.OrderedProduct;
import expertshop.domain.Product;
import expertshop.domain.User;
import expertshop.repos.OrderRepo;
import expertshop.repos.ProductRepo;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static expertshop.controllers.ControllerService.getSessionID;

@Log
@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public Page<Product> findProducts(String request, Pageable pageable, Model model)
    {
        log.info("Request " + request);

        Page<Product> page = findRequiredProducts(request, request, request, pageable);
        model.addAttribute("total", page.getTotalElements());

        log.info("Products found: " + page.getTotalElements() + " on " + page.getTotalPages() + " pages!");
        return page;
    }

    private Page<Product> findRequiredProducts(String s, String s1, String s2, Pageable pageable) {
        return productRepo.findByProductGroupContainingIgnoreCaseOrTypeContainingIgnoreCaseOrFullNameContainingIgnoreCase(s, s1, s2, pageable);
    }

    public List<Product> searchProducts(String searchRequest)
    {
        log.info("Search request: " + searchRequest);

        List<Product> searchedProducts = productRepo.findAll().stream()
                .filter(product -> StringUtils.containsIgnoreCase(product.getFullName(), searchRequest))
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







