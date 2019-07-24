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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static expertshop.controllers.ControllerService.getSessionID;

@Log
@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    /*public Page<Product> findOriginalProducts(String request, Pageable pageable, Model model) {
        Page<Product> products = productRepo.findByOriginalTypeContainingOrOriginalGroupContainingOrOriginalNameContaining(request, request, request, pageable);
        model.addAttribute("total", products.getTotalElements());
        return products;
    }*/

    public Page<Product> findOriginalProducts(String request, Pageable pageable) {
        return productRepo.findByOriginalTypeContainingOrOriginalNameContaining(request, request, pageable);
    }

    public Page<Product> findProducts(String request, Pageable pageable, Model model)
    {
        Page<Product> page = productRepo.findByProductGroupEqualsIgnoreCase(request, pageable);

        if (page.getTotalElements() == 0)
        {
            page = findOriginalProducts(request, pageable);
        }

        model.addAttribute("total", page.getTotalElements());
        return page;
    }

    public List<Product> searchProducts(String searchRequest)
    {
        log.info("Search request: " + searchRequest);
        String search = searchRequest.replaceAll(" ", "").replaceAll("-", "");
        log.info(search);

        List<Product> searchedProducts = productRepo.findAllByProductGroupIsNotNullAndIsDuplicateIsNull().stream()
                .filter(product -> StringUtils.containsIgnoreCase(product.getShortSearchName(), search))
                .collect(Collectors.toList());

        if (searchedProducts.size() == 0)
        {
            searchedProducts = productRepo.findAllByProductGroupIsNotNull().stream()
                    .filter(product -> StringUtils.containsIgnoreCase(product.getOriginalName(), searchRequest))
                    .collect(Collectors.toList());
        }

        log.info("Products found: " + searchedProducts.size());
        return searchedProducts;
    }

    public Set<String> getOrderedID(User user)
    {
        if (user != null && orderRepo.findByUserIDAndAcceptedFalse(user.getUserID()) != null)
        {
            Order order = orderRepo.findByUserIDAndAcceptedFalse(user.getUserID());
            return collectID(order);
        }
        else if (orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID()) != null)
        {
            Order order = orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID());
            return collectID(order);
        }
        else log.info("Order empty");
        return new HashSet<>();
    }

    Set<String> collectID(Order order) {
        Set<String> orderedProductsID = new HashSet<>();

        for (OrderedProduct product : order.getOrderedProducts())
            orderedProductsID.add(product.getProductID());

        return orderedProductsID;
    }

    public int[] getMinMaxPrice(String request) {
        List<Product> prices = productRepo.findByProductGroupEqualsIgnoreCase(request);
        if (prices.size() != 0)
        {
            log.info(request);
            log.info(prices.size()+"");

            prices.sort(Comparator.comparingInt(Product::getFinalPrice));
            int min = prices.stream().findFirst().get().getFinalPrice();

            prices.sort(Comparator.comparingInt(Product::getFinalPrice).reversed());
            int max = prices.stream().findFirst().get().getFinalPrice();

            return new int[]{min, max};
        }
        return null;
    }
}







