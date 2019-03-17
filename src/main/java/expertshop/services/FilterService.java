package expertshop.services;
import expertshop.domain.Product;
import expertshop.domain.lists.Categories;
import expertshop.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FilterService {
    private final
    ProductRepo productRepo;
    @Autowired
    public FilterService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    private String brand, country;
    private Integer sortMin = null, sortMax = null;
    /// Object howToSort;
    private Boolean hasBrand = false, hasCountry = false , hasSortMin = false, hasSortMax = false;

    ////-->V Параметры храняться в map имя_параметра-значение
    ////-->V Отсеить все isEmpty параметры через стрим !!!
    ////-->X* Создать объект Params c соответствующими параметрами
    ////-->V Достать параметры из массива и применить их в поиске
    ////-->X* Через Params product_params = new Params();
    ////-->V Вынести обработчики параметров в отдельные методы
    ////--> Создать логику наполнения модели отфильтрованными товарами
    ////    !!!СНАЧАЛА ФИЛЬТРЫ-ПАРАМЕТРЫ, ПОТОМ ЦЕНА И СОРТИРОВКА!!!

    public List<Product> mainFilterResolver(List<Product> products, Map<String, String> filterParams, Map<String, String> restrictionsParams) {
        // Отфильтровать пустые параментры  /// В МЕТОД!
        filterParams.values().removeIf(String::isEmpty);
        restrictionsParams.values().removeIf(String::isEmpty);

        showParams(filterParams);
        showParams(restrictionsParams);

        // Извлечение и наполнение параметров фильтра всех категорий
        getFilterParameters(filterParams);
        getRestrictionsParameters(restrictionsParams);

        products = constructFinalProductList(products);

        /*products = filterByPrice(products);*/

        return products;
    }

    private List<Product> constructFinalProductList(List<Product> products) throws NullPointerException {
        if (hasBrand() && !hasCountry) {
            products = productRepo.findByCategoryAndBrand(Categories.Electronics, getBrand());
            resetHasBrand();
            return products;
        }
        else if (hasCountry() && !hasBrand) {
            products = productRepo.findByCategoryAndCountry(Categories.Electronics, getCountry());
            resetHasCountry();
            return products;
        }
        else if (hasBrand() && hasCountry()) {
            products = productRepo.findByCategoryAndBrandOrCategoryAndCountry(Categories.Electronics, getBrand(), Categories.Electronics, getCountry());
            resetHasBrand();
            resetHasCountry();
            return products;
        }
        return products;
    }

    private void getFilterParameters(Map<String, String> filterParams) {
        for (Map.Entry<String, String> paramsWithArgs : filterParams.entrySet()) {
            String parameter = paramsWithArgs.getKey();
            String argument = paramsWithArgs.getValue();
            switch (parameter) {
                case("brand"): {
                    setBrand(argument);
                    setHasBrand();
                    break;
                }
                case("country"): {
                    setCountry(argument);
                    setHasCountry();
                    break;
                }
            }
        }
    }

    private void getRestrictionsParameters(Map<String, String> restrictionsParams) {
        for (Map.Entry<String, String> paramsWithArgs : restrictionsParams.entrySet()) {
            String parameter = paramsWithArgs.getKey();
            String argument = paramsWithArgs.getValue();
            if (parameter.equals("cheap") && !argument.equals("")) {
                setSortMin(Integer.parseInt(argument));
                setHasSortMax();
            }
            else if (parameter.equals("expensive") && !argument.equals("")) {
                setSortMax(Integer.parseInt(argument));
                setHasSortMax();
            }
        }
    }

    /// РАЗДЕЛИТЬ НА ПОДМЕТОДЫ!
    /*private List<Product> filterByPrice(List<Product> products) {
        Integer finalSortMin = sortMin;
        Integer finalSortMax = sortMax;
        if (hasSortMin & hasSortMax) {
            products = productRepo.findByCategory(Categories.Electronics);

            products = products.stream()
                    .filter(product -> (product.getPrice()) >= finalSortMin)
                    .filter(product -> (product.getPrice()) <= finalSortMax)
                    .collect(Collectors.toList());
            return products;
        }
        else if (hasSortMin) {
            products = productRepo.findByCategory(Categories.Electronics);

            products = products.stream()
                    .filter(product -> (product.getPrice()) >= finalSortMin)
                    .collect(Collectors.toList());
            return products;
        }
        else if (hasSortMax) {
            products = productRepo.findByCategory(Categories.Electronics);

            products = products.stream()
                    .filter(product -> (product.getPrice()) <= finalSortMax)
                    .collect(Collectors.toList());
            return products;
        }
        return products;
    }*/
    private void showParams(Map<String, String> filterParams) {
        System.out.println();
        for (Map.Entry<String, String> pair : filterParams.entrySet()) {
            String parameter = pair.getKey();
            String argument = pair.getValue();
            System.out.println(parameter + " " + argument);
        }
    }

    private Boolean hasBrand() {
        return hasBrand;
    }
    private Boolean hasCountry() {
        return hasCountry;
    }
    private Boolean hasSortMin() {
        return hasSortMin;
    }
    private Boolean hasSortMax() {
        return hasSortMax;
    }

    private String getBrand() {
        return brand;
    }
    private String getCountry() {
        return country;
    }

    private Integer getSortMin() {
        return sortMin;
    }
    private Integer getSortMax() {
        return sortMax;
    }

    private void setHasBrand() {
        this.hasBrand = true;
    }
    private void setHasCountry() {
        this.hasCountry = true;
    }
    private void setHasSortMin() {
        this.hasSortMin = true;
    }
    private void setHasSortMax() {
        this.hasSortMax = true;
    }
    private void resetHasBrand() {
        this.hasBrand = false;
    }
    private void resetHasCountry() {
        this.hasCountry = false;
    }
    private void resetHasSortMin() {
        this.hasSortMin = false;
    }
    private void resetHasSortMax() {
        this.hasSortMax = false;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }
    private void setCountry(String country) {
        this.country = country;
    }
    private void setSortMin(Integer sortMin) {
        this.sortMin = sortMin;
    }
    private void setSortMax(Integer sortMax) {
        this.sortMax = sortMax;
    }
}
