package expertshop.services;
import expertshop.domain.Product;
import expertshop.domain.lists.Categories;
import expertshop.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class FilterService {
    @Autowired
    ProductRepo productRepo;

    public List<Product> mainFilterResolver(List<Product> products, Map<String, String> filterParams, Map<String, String> restrictionsParams) {

        ////-->V Параметры храняться в map имя_параметра-значение
        ////-->V Отсеить все isEmpty параметры через стрим !!!
        ////-->X Создать объект Params c соответствующими параметрами
        ////-->V Достать параметры из массива и применить их в поиске
        ////-->X* Через Params product_params = new Params();*/

        String brand = null, country = null;

        showParams(filterParams);
        showParams(restrictionsParams);

        // Отфильтровать пустые параментры
        filterParams.values().removeIf(String::isEmpty);
        restrictionsParams.values().removeIf(String::isEmpty);

        showParams(filterParams);
        showParams(restrictionsParams);

        for (Map.Entry<String, String> paramsWithArgs : filterParams.entrySet()) {
            String parameter = paramsWithArgs.getKey();
            String argument = paramsWithArgs.getValue();

            switch (parameter) {
                case("brand"):
                    brand = argument;
                    products = productRepo.findByCategoryAndBrand(Categories.Electronics, brand);
                    break;
                case("country"):
                    country = argument;
                    products = productRepo.findByCategoryAndCountry(Categories.Electronics, country);
                    break;
            }
        }

        return products;
    }

    private void showParams(Map<String, String> filterParams) {
        System.out.println();
        for (Map.Entry<String, String> pair : filterParams.entrySet()) {
            String parameter = pair.getKey();
            String argument = pair.getValue();
            System.out.println(parameter + " " + argument);
        }
    }
}
