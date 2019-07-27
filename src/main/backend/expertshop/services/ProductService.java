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
        Page<Product> page = productRepo.findProductsByProductGroupEqualsIgnoreCaseAndIsAvailableTrue(request, pageable);

        /*if (page.getTotalElements() == 0) {
            page = findOriginalProducts(request, pageable);
        }*/

        model.addAttribute("total", page.getTotalElements());
        return page;
    }

    /*public Page<Product> findOriginalProducts(String request, Pageable pageable) {
        return productRepo.findByOriginalTypeContainingOrOriginalNameContaining(request, request, pageable);
    }*/

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
            try {
                prices.sort(Comparator.comparingInt(Product::getFinalPrice));
                int min = prices.stream().findFirst().get().getFinalPrice();

                prices.sort(Comparator.comparingInt(Product::getFinalPrice).reversed());
                int max = prices.stream().findFirst().get().getFinalPrice();

                return new int[]{min, max};
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Product> showReqProducts(String request, String isMapped, String withPic, Model model) {
        List<Product> products = new ArrayList<>();
        if (!request.isEmpty())
        {
            try {
                /// По категориям
                products = productRepo.findByProductCategoryEqualsIgnoreCase(request);
                products.sort(Comparator.comparing(Product::getProductGroup));
                if (!products.isEmpty()) return products;

                /// По группам
                products = productRepo.findByProductGroupEqualsIgnoreCase(request);
                if (products.size() != 0)
                {
                    products.sort(Comparator.comparingDouble(Product::getDefaultCoefficient).reversed());
                    double defCoefficient = products.stream().findFirst().get().getDefaultCoefficient();
                    model.addAttribute("coefficient", defCoefficient);

                    List<Product> modCoeff = productRepo.findByProductGroupEqualsIgnoreCase(request).stream().filter(product -> product.getModCoefficient() != null).collect(Collectors.toList());
                    if (!modCoeff.isEmpty()) {
                        double modCoefficient = products.stream().filter(product -> product.getModCoefficient() != null).findFirst().get().getModCoefficient();
                        model.addAttribute("modCoefficient", modCoefficient);
                    }

                    products.sort(Comparator.comparing(Product::getOriginalBrand));
                    return products;
                }



                String shortRequest = StringUtils.lowerCase(request).replaceAll(" ","").replaceAll("-", "");
                products = productRepo.findByShortSearchNameContains(shortRequest);
                if (products.size() != 0) return products;

                if (isMapped != null) {
                    products = productRepo.findBySupplierContainsIgnoreCaseAndProductGroupIsNotNull(request);
                }
                else  products = productRepo.findBySupplierContainsIgnoreCase(request);
                if (products.size() != 0) return products;
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
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
            product.setPriceMod(true);
            productRepo.save(product);
        });
        return true;
    }

    public void saveNewCoeff(String[] coeff) {
        List<Product> list = productRepo.findByProductGroupEqualsIgnoreCase(coeff[0]);
        list.forEach(product ->
        {
            String brand = product.getOriginalBrand();
            String[] stopBrands = {"AMCV", "ARDIN", "BINATONE", "DOFFLER", "LERAN", "SENTORE"};
            List<String> stopList = Arrays.asList(stopBrands);

            if (!stopList.contains(brand.toUpperCase()))
            {
                product.setModCoefficient(Double.parseDouble(coeff[1].replaceAll(",", ".")));
                product.setCoefficientMod(true);
                int newPrice = roundPrice(Double.parseDouble(coeff[1].replaceAll(",", ".")), (int) Double.parseDouble(product.getOriginalPrice().replaceAll(",", ".").replaceAll(" ", "")));
                product.setFinalPrice(newPrice);
                productRepo.save(product);

                log.info(product.getModCoefficient().toString());
                log.info(product.getFinalPrice().toString());
            }
        });
    }
    public int roundPrice(double coefficient, int price) {
        int finalPrice = (int) (price * coefficient);
        String val = String.valueOf(finalPrice);

        if (finalPrice > 0 && finalPrice <= 10) {
            return 10;
        }
        else if (finalPrice > 10 && finalPrice < 1000) {
            val = val.substring(0, val.length()-1).concat("9");
            return Integer.parseInt(val);
        }
        else if (finalPrice > 1000) {
            val = val.substring(0, val.length()-2).concat("90");
            return Integer.parseInt(val);
        }
        else return finalPrice;
    }

    public boolean removePriceMod(String productID) {
        log.info(productID);
        Product product = productRepo.findByProductID(productID);
        log.info(product.getOriginalName());
        product.setPriceMod(null);
        productRepo.save(product);
        return true;
    }

    public boolean removeModCoeff(String productID) {
        log.info(productID);
        String request = StringUtils.substringBefore(productID, ";");
        double defaultCoeff = Double.parseDouble(StringUtils.substringAfter(productID, ";").replaceAll(",", "."));

        List<Product> list = productRepo.findByProductGroupEqualsIgnoreCase(request);
        list.forEach(product ->
        {
            String brand = product.getOriginalBrand();
            String[] stopBrands = {"AMCV", "ARDIN", "BINATONE", "DOFFLER", "LERAN", "SENTORE"};
            List<String> stopList = Arrays.asList(stopBrands);

            if (!stopList.contains(brand.toUpperCase()))
            {
                product.setModCoefficient(null);
                product.setCoefficientMod(null);
                product.setDefaultCoefficient(defaultCoeff);

                int newPrice = roundPrice(defaultCoeff, (int) Double.parseDouble(product.getOriginalPrice().replaceAll(",", ".").replaceAll(" ", "")));
                product.setFinalPrice(newPrice);
                productRepo.save(product);

               /* log.info(product.getModCoefficient().toString());
                log.info(product.getFinalPrice().toString());*/
            }
        });
        return true;
    }
}







