package expertshop.controllers;
import expertshop.domain.Params;
import expertshop.domain.Product;
import expertshop.domain.categories.Type;
import expertshop.repos.ProductRepo;
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

    @GetMapping("/tv-4K")
    public String showTV4K(
            Model model, @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin, @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax, @RequestParam(required = false, name = "brand", defaultValue = "") String brand, @RequestParam(required = false, name = "country", defaultValue = "") String country, @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby

    ){
        Params tvParams = new Params();
        tvParams.setResolution_type("4K");

        /*List<Product> products = productRepo.findByTypeAndParametersEquals(Type.TV, tvParams);*/

        /*model.addAttribute("products", products);*/
        return "pages/electronics/tv";
    }
}
