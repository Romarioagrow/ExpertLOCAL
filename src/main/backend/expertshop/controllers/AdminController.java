package expertshop.controllers;

import expertshop.domain.User;
import expertshop.products.CatalogParser;
import expertshop.products.ProductMatcher;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log
@Controller
@AllArgsConstructor
@RequestMapping("/supplier")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final CatalogParser catalogParser;
    private final ProductMatcher productMatcher;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String supplier()
    {
        return "pages/supplier";
    }

    @PostMapping("/parse-catalog")
    public String loadCSV (@RequestParam("file") MultipartFile file, Model model, @AuthenticationPrincipal User user) throws IOException
    {
        log.fine("PARSING FILE: " + file.getOriginalFilename());
        catalogParser.processFile(file);
        return "pages/supplier";
    }

    @PostMapping("/match-products")
    public String matchProducts()
    {
        log.fine("PRODUCT MATCHER RUNNING!");
        productMatcher.matchProducts();
        return "pages/supplier";
    }

    @GetMapping("/supplier/pics")
    public String checkProductPics()
    {
        ///catalogParser.checkProductPics();
        return "pages/supplier";
    }
}