package expertshop.controllers;
import expertshop.products.ProductParser;
import expertshop.products.ProductMatcher;
import expertshop.repos.OrderRepo;
import expertshop.services.OrderService;
import expertshop.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log
@Controller
@AllArgsConstructor
@RequestMapping("/supplier")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final ProductParser productParser;
    private final ProductMatcher productMatcher;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderRepo orderRepo;

    @PostMapping("/updateDB")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String loadCSV(@RequestParam("file") MultipartFile file, Model model) {
        productMatcher.updateProductDB(file);
        model.addAttribute("url", "db");
        return "pages/supplier";
    }

    @PostMapping("/updateBrandsProducts")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateBrandProducts(@RequestParam("brandsFile") MultipartFile file, Model model) {
        productParser.parseBrandProducts(file);
        model.addAttribute("url", "db");
        return "pages/supplier";
    }

    @PostMapping("/products/uploadpic")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadPic(Model model,
                            @RequestParam("file") MultipartFile file,
                            @RequestParam (value = "upload", required = false) String productID) {
        productMatcher.uploadProductPic(file, productID);
        model.addAttribute("url", "products");
        return "pages/supplier";
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String supplier(Model model) {
        model.addAttribute("url", "db");
        return "pages/supplier";
    }

    @GetMapping("/products")
    @PreAuthorize("hasAuthority('ADMIN')")
    private String products(Model model,
                            @RequestParam String request,
                            @RequestParam(value = "mapped", required = false) String mapped,
                            @RequestParam(value = "withpic", required = false) String withpic) {
        model.addAttribute("url", "products");
        model.addAttribute("request", request);
        model.addAttribute("products", productService.showReqProducts(request.trim(), mapped, withpic, model));
        return "pages/supplier";
    }

    @GetMapping("/orders")
    private String orders(Model model, @RequestParam String request) {
        model.addAttribute("url", "orders");
        model.addAttribute("orders", orderService.showAcceptedOrders(request));
        return "pages/supplier";
    }

    @GetMapping("/completeOrders")
    private String completeOrders(Model model) {
        model.addAttribute("url", "completeOrders");
        model.addAttribute("completeOrders", orderRepo.findAllByCompletedTrue());
        return "pages/supplier";
    }

    @PostMapping("/orders/complete")
    private String completeOrder(Model model, @RequestParam(value = "orderID", required = false) String orderID) {
        model.addAttribute("url", "orders");
        orderService.completeOrder(orderID);
        return "redirect:/supplier/orders?request=";
    }
    @PostMapping("/orders/removeorder")
    private String removeOrder(Model model, @RequestParam(value = "orderID", required = false) String orderID) {
        model.addAttribute("url", "orders");
        orderService.removeOrder(orderID);
        return "redirect:/supplier/orders?request=";
    }

    @PostMapping("/xxx")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String matchDuplicates(Model model) {
        productMatcher.xxx();
        model.addAttribute("url", "db");
        return "pages/supplier";
    }

    @PostMapping("/pics")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String checkProductPics(Model model) {
        productParser.parseRusBT();
        model.addAttribute("url", "db");
        return "pages/supplier";
    }
}
