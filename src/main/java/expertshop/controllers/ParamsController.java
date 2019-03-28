package expertshop.controllers;
import expertshop.domain.ProductWrap;
import expertshop.domain.categories.Type;
import expertshop.repos.WrapRepo;
import expertshop.services.FilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ParamsController {
    private final WrapRepo wrapRepo;
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
        filterService.constructAndFilterTV(model, sortmin, sortmax, brand, country, sortby);
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
        List<ProductWrap> productsWithParams = new ArrayList<>();

        if (sortmin.isEmpty()) sortmin = null; else
        if (sortmax.isEmpty()) sortmax = null;
        if (brand.isEmpty()) brand = null;
        if (country.isEmpty()) country = null;

        String finalSortmin = sortmin;
        String finalSortmax = sortmax;
        String finalBrand = brand;
        String finalCountry = country;

        productsWithParams = productsWithParams.stream()
                .filter(productWrap -> productWrap.getProduct().getPrice() >= Integer.parseInt(finalSortmin))//ifNotEquals("")
                .filter(productWrap -> productWrap.getProduct().getPrice() <= Integer.parseInt(finalSortmax))//ifNotEquals("")
                .filter(productWrap -> productWrap.getProduct().getBrand().equals(finalBrand)) //ifNotEquals("")
                .filter(productWrap -> productWrap.getProduct().getCountry().equals(finalCountry))//ifNotEquals("")
                .collect(Collectors.toList());

        productsWithParams = wrapRepo.findByType(Type.TV);


        /*productsWithParams = productsWithParams.stream()
         *//*.filter(productWrap -> Objects.equals(productWrap.getProductParams().getResolution(), "4K 3840x2160"))*//*
                .filter(productWrap -> if (sortmin == null) {

        } )
                .collect(Collectors.toList());*/

        model.addAttribute("productsWithParams", productsWithParams);
        return "pages/test_page";
    }


}

///--->V ОБЯЗАТЕЛЬНЫЙ ПРОХОД ЧЕРЕЗ ВСЕ ФИЛЬТРЫ, ЕСЛИ isEmpty ТО ПРОПУСКАЕТСЯ!!!
