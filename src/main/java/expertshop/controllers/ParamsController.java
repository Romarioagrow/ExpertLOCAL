package expertshop.controllers;
import expertshop.domain.categories.Type;
import expertshop.services.FilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ParamsController {
    private final FilterService filterService;

    @GetMapping("/tv-4K")
    public String showTV4K(
            Model model,
            @RequestParam(required = false, name = "sortmin") String sortmin,
            @RequestParam(required = false, name = "sortmax") String sortmax,
            @RequestParam(required = false, name = "brand") String brand,
            @RequestParam(required = false, name = "country") String country,
            @RequestParam(required = false, name = "sortby") String sortby
    ){
        filterService.constructAndFilter(Type.TV, model, sortmin, sortmax, brand, country, sortby);
        return "pages/electronics/tv";
    }


    @GetMapping("/test")
    public String showTest(
            Model model,
            @RequestParam(required = false, name = "sortmin", defaultValue = "") String sortmin,
            @RequestParam(required = false, name = "sortmax", defaultValue = "") String sortmax,
            @RequestParam(required = false, name = "brand", defaultValue = "") String brand,
            @RequestParam(required = false, name = "country", defaultValue = "") String country,
            @RequestParam(required = false, name = "sortby", defaultValue = "") String sortby

    ){
        filterService.showAllProducts(model, sortmin, sortmax, brand, country, sortby);
        return "pages/test_page";
    }


}

///--->V ОБЯЗАТЕЛЬНЫЙ ПРОХОД ЧЕРЕЗ ВСЕ ФИЛЬТРЫ, ЕСЛИ isEmpty ТО ПРОПУСКАЕТСЯ!!!
