package expertshop.controllers;
import expertshop.domain.Product;
import expertshop.domain.User;
import expertshop.domain.dto.OrderContacts;
import expertshop.repos.UserRepo;
import expertshop.services.FilterService;
import expertshop.services.OrderService;
import expertshop.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Log
@RestController
@AllArgsConstructor
public class ServiceController {
    private final FilterService filterService;
    private final ProductService productService;
    private final OrderService orderService;
    private final UserRepo userRepo;

    @PostMapping("/catalog")
    private Set<String> displayCatalogGroups(@RequestBody String productGroup) {
        return productService.displayCatalogGroups(productGroup);
    }

    @PostMapping("/supplier/products/removemodcoff")
    @PreAuthorize("hasAuthority('ADMIN')")
    private boolean removeModCoeff(@RequestBody String productID) {
        return productService.removeModCoeff(productID);
    }

    @PostMapping("/supplier/products/removemod")
    @PreAuthorize("hasAuthority('ADMIN')")
    private boolean removePriceMod(@RequestBody String productID) {
        return productService.removePriceMod(productID);
    }

    @PostMapping("/supplier/products/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    private boolean saveProducts(@RequestBody Map<String, String> data) {
        return productService.editProducts(data);
    }

    @PostMapping("/supplier/products/defaultCoefficient")
    @PreAuthorize("hasAuthority('ADMIN')")
    private String saveNewCoefficient(@RequestBody String[] coeff) {
        log.info(Arrays.toString(coeff));
        productService.saveNewCoeff(coeff);
        return "pages/supplier";
    }

    /*PRODUCTS FILTERS*/
    @PostMapping("/products/{category}/{reqType}")
    public LinkedList<Object> filterProducts(
            @RequestBody Map<String, String> params,
            @PathVariable String reqType,
            @AuthenticationPrincipal User user,
            @PathVariable String category,
            @PageableDefault(sort = {"supplier"}, direction = Sort.Direction.ASC, size = 100) Pageable pageable
    ){
        String request = StringUtils.capitalize(reqType.replaceAll("_", " "));
        return filterService.filterProducts(params, request, pageable, user);
    }

    @PostMapping("/brands")
    public LinkedList<Object> displayBrands(
            @RequestBody(required = false) String productGroup
    ){
        if (productGroup != null) {
            productGroup = productGroup.replaceAll("_", " ");
            return filterService.resolveFilters(productGroup);
        }
        return null;
    }

    /*PRODUCTS SEARCH*/
    @PostMapping("/search")
    public List<Product> searchProducts(
            @RequestBody String searchRequest
    ){
        return productService.searchProducts(searchRequest);
    }

    /*ORDER*/
    @PostMapping("/order")
    private LinkedList<Integer> addProductToOrder(
            @AuthenticationPrincipal User user, @RequestBody String productID
    ){
        return orderService.addProductToOrder(productID, user);
    }
    @PutMapping("/order")
    private LinkedList<Object> changeAmount(
            @AuthenticationPrincipal User user, @RequestBody Map<String, String> data
    ) throws NullPointerException {
        if (user != null)
        {
            User reloadUser = userRepo.findByUserID(user.getUserID());
            return orderService.changeAmount(reloadUser, data);
        }
        return orderService.changeAmount(null, data);
    }
    @DeleteMapping("/order")
    private LinkedList<Object> removeProductFromOrder(
            @AuthenticationPrincipal User user, @RequestBody String productID
    ){
        return orderService.removeProductFromOrder(user, productID);
    }
    @PostMapping("/order/confirm")
    private Object confirmOrder (@AuthenticationPrincipal User user, @Valid @RequestBody OrderContacts contacts, BindingResult validResult)
    {
        if (validResult.hasErrors()) {
            return ControllerService.getValidErrorsSet(validResult);
        }
        else
        {
            if (user != null) {
                User reloadUser = userRepo.findByUserID(user.getUserID());
                return orderService.confirmOrder(contacts, reloadUser);
            }
            return orderService.confirmOrder(contacts, user);
        }
    }
    @PostMapping("/order/discount")
    private LinkedList<Object> applyDiscount(@RequestBody Map<String, String> discountData, @AuthenticationPrincipal User user) {
        User reloadUser = userRepo.findByUserID(user.getUserID());
        return orderService.applyDiscount(discountData, reloadUser);
    }
    @PutMapping("/order/discount")
    private LinkedList<Object> dropDiscount(@AuthenticationPrincipal User user, @RequestBody String orderID) throws NullPointerException {
        if (user != null) {
            User reloadUser = userRepo.findByUserID(user.getUserID());
            return orderService.dropDiscount(orderID, reloadUser);
        }
        return orderService.dropDiscount(orderID, null);
    }
}
