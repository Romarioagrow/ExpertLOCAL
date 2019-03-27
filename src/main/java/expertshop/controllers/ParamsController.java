package expertshop.controllers;
import expertshop.domain.ProductWrap;
import expertshop.repos.ParamsRepo;
import expertshop.repos.ProductRepo;
import expertshop.repos.WrapRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
@AllArgsConstructor
public class ParamsController {
    private final ProductRepo productRepo;
    private final ParamsRepo paramsRepo;
    private final WrapRepo wrapRepo;

    @GetMapping("/test")
    public String showTV4K(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby

    ){
        List<ProductWrap> productsWithParams;

        productsWithParams = wrapRepo.findAll();

        model.addAttribute("productsWithParams", productsWithParams);
        return "pages/test_page";
    }
}
