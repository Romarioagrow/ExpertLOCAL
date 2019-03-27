package expertshop.controllers;
import expertshop.domain.ProductWrap;
import expertshop.repos.ProductRepo;
import expertshop.repos.WrapRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ParamsController {
    private final ProductRepo productRepo;
    /*private final ParamsRepo paramsRepo;*/
    private final WrapRepo wrapRepo;

    @GetMapping("/test")
    public String showTest(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby

    ){
        List<ProductWrap> productsWithParams;

        productsWithParams = wrapRepo.findAll();

        /*productsWithParams = productsWithParams.stream()
                .filter(productWrap -> productWrap.getProductParams().getResolutionType() != null)
                .collect(Collectors.toList());*/

        productsWithParams = productsWithParams.stream()
                .filter(productWrap -> Objects.equals(productWrap.getProductParams().getResolution(), "4K 3840x2160"))
                .collect(Collectors.toList());

        model.addAttribute("productsWithParams", productsWithParams);
        return "pages/test_page";
    }

    @GetMapping("/tv-4K")
    public String showTV4K(Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby
    ){
        List<ProductWrap> productsWithParams = wrapRepo.findAll();

        productsWithParams = productsWithParams.stream()
                .filter(productWrap -> Objects.equals(productWrap.getProductParams().getResolution(), "4K 3840x2160"))
                .collect(Collectors.toList());

        model.addAttribute("productsWithParams", productsWithParams);

        return "pages/electronics/tv";
    }

}

/// ДЛЯ ProductWrap
/// СОБРАТЬ ВСЕ ПАРАМЕТРЫ NOTNULL И ПРИМЕНИТЬ ИХ В STREAM ФИЛЬТР