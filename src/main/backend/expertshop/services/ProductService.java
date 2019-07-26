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

import java.util.*;
import java.util.stream.Collectors;

import static expertshop.controllers.ControllerService.getSessionID;

@Log
@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public Page<Product> findProducts(String request, Pageable pageable, Model model) {
        Page<Product> page = productRepo.findProductsByProductGroupEqualsIgnoreCase(request, pageable);

        if (page.getTotalElements() == 0) {
            page = findOriginalProducts(request, pageable);
        }

        model.addAttribute("total", page.getTotalElements());
        return page;
    }

    public Page<Product> findOriginalProducts(String request, Pageable pageable) {
        return productRepo.findByOriginalTypeContainingOrOriginalNameContaining(request, request, pageable);
    }

    public List<Product> searchProducts(String searchRequest) {
        //log.info("Search request: " + searchRequest);
        String search = searchRequest.replaceAll(" ", "").replaceAll("-", "").toLowerCase();
        //log.info(search);
        /*if (searchedProducts.size() == 0)
        {
            searchedProducts = productRepo.findAllByProductGroupIsNotNull().stream()
                    .filter(product -> StringUtils.containsIgnoreCase(product.getOriginalName(), searchRequest))
                    .collect(Collectors.toList());
        }*/
        //log.info("Products found: " + searchedProducts.size());
        return productRepo.findAllByProductGroupIsNotNullAndIsDuplicateIsNullAndShortSearchNameContainsIgnoreCase(search).stream()
                .filter(product -> StringUtils.containsIgnoreCase(product.getShortSearchName(), search))
                .collect(Collectors.toList());
    }

    public Set<String> getOrderedID(User user)
    {
        if (user != null && orderRepo.findByUserIDAndAcceptedFalse(user.getUserID()) != null) {
            Order order = orderRepo.findByUserIDAndAcceptedFalse(user.getUserID());
            return collectID(order);
        }
        else if (orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID()) != null) {
            Order order = orderRepo.findBySessionUUIDAndAcceptedFalse(getSessionID());
            return collectID(order);
        }
        //else log.info("Order empty");
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
            /*log.info(request);
            log.info(prices.size()+"");*/
            prices.sort(Comparator.comparingInt(Product::getFinalPrice));
            int min = prices.stream().findFirst().get().getFinalPrice();

            prices.sort(Comparator.comparingInt(Product::getFinalPrice).reversed());
            int max = prices.stream().findFirst().get().getFinalPrice();

            return new int[]{min, max};
        }
        return null;
    }

    public List<Product> showReqProducts(String request, String isMapped, String withPic, Model model) {
        List<Product> products = new ArrayList<>();

        if (!request.isEmpty())
        {
            String shortRequest = request.replaceAll(" ","").replaceAll("-", "").toLowerCase();
            products = productRepo.findByShortSearchNameContains(shortRequest);
            if (products.size() != 0) return products;

            products = productRepo.findByProductGroupEqualsIgnoreCase(request);
            if (products.size() != 0) {
                model.addAttribute("coefficient", products.stream().findFirst().get().getCoefficient());
                return products;
            }

            products = productRepo.findByProductCategoryEqualsIgnoreCase(request);
            if (products.size() != 0) return products;

            if (isMapped != null) {
                products = productRepo.findBySupplierContainsIgnoreCaseAndProductGroupIsNotNull(request);
            }
            else  products = productRepo.findBySupplierContainsIgnoreCase(request);
            if (products.size() != 0) return products;
        }

        if (request.isEmpty() && isMapped != null) {
            products = productRepo.findAllByModelNameNotNull();
        }
        else if (request.isEmpty()) {
            products = productRepo.findAll();
        }

        return products;
    }

    public boolean editProducts(Map<String, String> data) {
        data.forEach((productID, newPrice) ->
        {
            //log.info(productID + ": " + newPrice);
            Product product = productRepo.findByProductID(productID);
            product.setFinalPrice(Integer.parseInt(newPrice.replaceAll("\\W", "")));
            productRepo.save(product);
        });
        return true;
    }

    public void saveNewCoeff(String[] coeff) {
        List<Product> list = productRepo.findByProductGroupEqualsIgnoreCase(coeff[0]);
        list.forEach(product -> {
            product.setCoefficient(Double.parseDouble(coeff[1].replaceAll(",", ".")));
            productRepo.save(product);
            //log.info(product.getCoefficient().toString());
        });
    }
}







