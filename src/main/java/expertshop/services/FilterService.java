package expertshop.services;
import expertshop.domain.Product;
import expertshop.domain.lists.Categories;
import expertshop.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FilterService {
    private final
    ProductRepo productRepo;

    @Autowired
    public FilterService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> mainFilterResolver(List<Product> products, Map<String, String> filterParams, Map<String, String> restrictionsParams) {

        ////-->V Параметры храняться в map имя_параметра-значение
        ////-->V Отсеить все isEmpty параметры через стрим !!!
        ////-->X Создать объект Params c соответствующими параметрами
        ////-->V Достать параметры из массива и применить их в поиске
        ////-->X* Через Params product_params = new Params();*/

        String brand = null, country = null;
        Integer sortMin = null, sortMax = null;

        showParams(filterParams);
        showParams(restrictionsParams);

        // Отфильтровать пустые параментры
        filterParams.values().removeIf(String::isEmpty);
        restrictionsParams.values().removeIf(String::isEmpty);

        showParams(filterParams);
        showParams(restrictionsParams);

        /// В МЕТОД!!!
        // Извлечение, наполнение и применение параметров
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

        /// В МЕТОД!!!
        // Извлечение, наполнение ограничивающих параметров
        for (Map.Entry<String, String> paramsWithArgs : restrictionsParams.entrySet()) {
            String parameter = paramsWithArgs.getKey();
            String argument = paramsWithArgs.getValue();

            if (parameter.equals("cheap") && !argument.equals("")) {
                sortMin = Integer.parseInt(argument);
            }
            else if (parameter.equals("expensive") && !argument.equals("")) {
                sortMax = Integer.parseInt(argument);
            }
        }

        /// В МЕТОД!!!
        // Фильтрация по ценам
        Integer finalSortMin = sortMin;
        Integer finalSortMax = sortMax;
        if (sortMin != null & sortMax != null) {
            products = productRepo.findByCategory(Categories.Electronics);

            products = products.stream()
                    .filter(product -> (product.getPrice()) >= finalSortMin)
                    .filter(product -> (product.getPrice()) <= finalSortMax)
                    .collect(Collectors.toList());
            return products;
        }
        else if (sortMin != null) {
            products = productRepo.findByCategory(Categories.Electronics);

            products = products.stream()
                    .filter(product -> (product.getPrice()) >= finalSortMin)
                    .collect(Collectors.toList());
            return products;
        }
        else if (sortMax != null) {
            products = productRepo.findByCategory(Categories.Electronics);

            products = products.stream()
                    .filter(product -> (product.getPrice()) <= finalSortMax)
                    .collect(Collectors.toList());
            return products;
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
