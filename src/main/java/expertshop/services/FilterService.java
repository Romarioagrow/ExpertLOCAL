package expertshop.services;
import expertshop.domain.ProductWrap;
import expertshop.domain.categories.Type;
import expertshop.repos.WrapRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilterService {
    private final WrapRepo wrapRepo;

    public void constructAndFilterTV(Model model, String sortmin, String sortmax, String brand, String country, String sortby) {
        if (formIsActive(sortmin, sortmax, brand, country)) {
            List<ProductWrap> productsWithParams = wrapRepo.findByType(Type.TV);

            if (!sortmin.isEmpty()) {
                productsWithParams = productsWithParams.stream().filter(productWrap -> productWrap.getProduct().getPrice() >= Integer.parseInt(sortmin)).collect(Collectors.toList());
            }
            if (!sortmax.isEmpty()) {
                productsWithParams = productsWithParams.stream().filter(productWrap -> productWrap.getProduct().getPrice() <= Integer.parseInt(sortmax)).collect(Collectors.toList());
            }
            if (!brand.isEmpty() | !country.isEmpty()) {
                productsWithParams = productsWithParams.stream().filter(productWrap -> productWrap.getProduct().getBrand().equals(brand) | productWrap.getProduct().getCountry().equals(country)).collect(Collectors.toList());
            }
            model.addAttribute("productsWithParams", productsWithParams);
        }
        else {
            List<ProductWrap> productsWithParams = wrapRepo.findByType(Type.TV);
            model.addAttribute("productsWithParams", productsWithParams);
        }
    }

    private boolean formIsActive(String sortmin, String sortmax, String brand, String country) {
        return (sortmin != null | sortmax != null | brand != null | country != null);
    }

    /// СОБРАТЬ ВСЕ ПАРАМЕТРЫ NOTNULL И ПРИМЕНИТЬ ИХ В STREAM ФИЛЬТР!!!
    /*productsWithParams = productsWithParams.stream()
     *//*.filter(productWrap -> Objects.equals(productWrap.getProductParams().getResolution(), "4K 3840x2160"))*//* //resolution
                .filter(productWrap -> productWrap.getProduct().getPrice() >= Integer.parseInt(sortmin))//ifNotEquals("")
                .filter(productWrap -> productWrap.getProduct().getPrice() <= Integer.parseInt(sortmax))//ifNotEquals("")
                .filter(productWrap -> productWrap.getProduct().getBrand().equals(brand)) //ifNotEquals("")
                .filter(productWrap -> productWrap.getProduct().getCountry().equals(country))//ifNotEquals("")
                .collect(Collectors.toList());*/



}

