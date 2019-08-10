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
        Page<Product> page = productRepo.findProductsByProductGroupEqualsIgnoreCaseAndIsDuplicateIsNullAndIsAvailableTrue(request, pageable);
        if (page.getTotalElements() != 0) {
            model.addAttribute("total", page.getTotalElements());
            return page;
        }
        else
        { ///displayUnmappedProducts();
            request = StringUtils.capitalize(request.replaceAll("_", " "));
            page = productRepo.findByOriginalTypeAndIsAvailableIsTrue(request, pageable);

            String[] accessories = {
                    "Клей, герметик, жидкие гвозди","Приспособления для кладки плитки","Изолента, скотч, ленты","Сверла, фрезы","Электроды","Шнуры и веревки хозяйственные","Тросы, цепи",
                    "Аксессуары для сварки","Батареи для шуруповертов","Средства защиты","Щетки для шлифмашин","Диски пильные","Запчасти и приспособления","Приспособления для доильных аппаратов","Ср-ва защиты от грызунов",
                    "Ср-ва защиты от насекомых","Пленка","Крышки металлические","Крышки винтовые","Крышки полиэтиленовые","Запчасти и приспособления к машинкам","Подводка для газа","Баллончики газовые туристические",
                    "Уголь и средства для розжига"
            };

            page.forEach(product ->
            {
                //if (product.getFinalPrice() == null)
                {
                    double coeff;
                    int finalPrice = (int) Double.parseDouble(product.getOriginalPrice().replaceAll(",",".").replaceAll(" ",""));
                    if (Arrays.asList(accessories).contains(product.getOriginalType()))
                        coeff = 1.5;
                    else coeff = 1.2;
                    finalPrice = roundPrice(coeff, finalPrice);

                    product.setFinalPrice(finalPrice);
                    product.setBonus(matchBonus(finalPrice));
                    product.setDefaultCoefficient(coeff);
                    productRepo.save(product);
                }
            });
            //String[] path = {StringUtils.capitalize(request), StringUtils.capitalize(request)};
            model.addAttribute("total", page.getTotalElements());
            //model.addAttribute("path",  path);
            return page;
        }
    }

    public List<Product> searchProducts(String searchRequest) {
        String search = searchRequest.replaceAll(" ", "").replaceAll("-", "").toLowerCase();
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
        if (!prices.isEmpty())
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
            try
            {
                /// По категориям
                products = productRepo.findByProductCategoryEqualsIgnoreCase(request);
                if (!products.isEmpty()) {
                    products.sort(Comparator.comparing(Product::getProductGroup));
                    return products;
                }

                /// По группам
                products = productRepo.findByProductGroupEqualsIgnoreCase(request);
                if (!products.isEmpty())
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

                /// По брендам
                products = productRepo.findByProductGroupNotNullAndOriginalBrandContainsIgnoreCase(request);
                if (!products.isEmpty()) {
                    products.sort(Comparator.comparing(Product::getProductGroup));
                    return products;
                }

                /// По наименованию
                String shortRequest = StringUtils.lowerCase(request).replaceAll(" ","").replaceAll("-", "");
                products = productRepo.findByShortSearchNameContains(shortRequest);
                if (!products.isEmpty()) return products;


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
            }
        });
        return true;
    }

    public Map<String, String> displayAnnotation(String productID, Model model) {
        Map<String, String> stringMap = new LinkedHashMap<>();
        Product product = productRepo.findByProductID(productID);
        String formAnno = product.getFormattedAnnotation();

        if (formAnno != null && !formAnno.isEmpty())
        {
            String[] annotation;

            if (formAnno.contains("</tr>")) {
                annotation = formAnno.replaceAll("\n","").split("</tr>");
                for (String s : annotation)
                {
                    String annoKey = StringUtils.substringBeforeLast(s, "<td>").replaceAll("[<table></table><tr><td></td>]","");
                    String annoValue = StringUtils.substringAfterLast(s, "<td>").replaceAll("[<td></td>]","");;
                    stringMap.put(annoKey, annoValue);
                }
                return stringMap;
            }

            if (formAnno.contains("<br>"))
            {
                annotation = formAnno.split("<br>");
                for (String splitParam : annotation)
                {
                    String annoKey = StringUtils.substringBefore(splitParam, ":").trim();
                    String annoValue = StringUtils.substringAfter(splitParam, ":").trim();

                    if (annoKey.startsWith("количество шт в")) {
                        continue;
                    }

                    if (annoValue.startsWith("-") || annoValue.equals("0")) {
                        continue;
                    }
                    stringMap.put(annoKey, annoValue);
                }
                return stringMap;
            }
        }
        else
        {
            formAnno = product.getOriginalAnnotation();
            if (formAnno!= null && !formAnno.isEmpty())
            {
                if (formAnno.contains(", "))
                {
                    String[] annotation = formAnno.split(", ");
                    model.addAttribute("listAnno", annotation);
                    return null;
                }
            }
        }
        return null;
    }

    public Set<String> displayCatalogGroups(String productCategory) {
        Set<String> productGroups = new HashSet<>();
        productCategory = StringUtils.capitalize(productCategory.replaceAll("_", " "));
        log.info(productCategory);

        productRepo.findByProductGroupIsNullAndSupplierEqualsAndOriginalCategoryContains("2RUS-BT" ,productCategory).forEach(product -> {
            productGroups.add(product.getOriginalType());
        });
        return productGroups;
    }

    public Integer matchBonus(int price) {
        int bonus = price * 3 / 100;
        String val = String.valueOf(bonus);

        if (bonus > 0 && bonus <= 10) {
            return 10;
        }
        else {
            val = val.substring(0, val.length()-1).concat("0");
            return Integer.parseInt(val);
        }
    }
}







