package expertshop.services;
import expertshop.domain.Product;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.domain.categories.Type;
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
    private Boolean hasBrand = false, hasCountry = false , hasSortMin = false, hasSortMax = false;

    public List<Product> mainCategoryFilterResolver(Map<String, String> allFilterParams, Category category)
    {
        getFilterParameters(allFilterParams);
        return constructFinalProductList(category);
    }
    public List<Product> mainSubFilterResolver(Map<String, String> allFilterParams, SubCategory subCategory)
    {
        getFilterParameters(allFilterParams);
        return constructFinalProductList(subCategory);
    }
    public List<Product> mainTypeFilterResolver(Map<String, String> allFilterParams, Type type)
    {
        getFilterParameters(allFilterParams);
        return constructFinalProductList(type);
    }
    public List<Product> mainFilterResolver(Map<String, String> allFilterParams)
    {
        getFilterParameters(allFilterParams);
        return constructFinalProductList();
    }

    private List<Product> constructFinalProductList(Category category) throws NullPointerException {
        List<Product> products;
        /// В МЕТОД ФИЛЬТРА ПО ПАРАМЕТРАМ!
        {
            if (hasBrand() && !hasCountry())
            {
                products = productRepo.findByCategoryAndBrand(category, getBrand());
                resetHasBrand();
            }
            else if (hasCountry() && !hasBrand())
            {
                products = productRepo.findByCategoryAndCountry(category, getCountry());
                resetHasCountry();
            }
            else if (hasBrand() && hasCountry())
            {
                products = productRepo.findByCategoryAndBrandOrCategoryAndCountry(category, getBrand(), category, getCountry());
                resetHasBrand();
                resetHasCountry();
            }
            else products = productRepo.findByCategory(category);
        }
        return filterByPrice(products);
    }
    private List<Product> constructFinalProductList(SubCategory subCategory) throws NullPointerException {
        List<Product> products;
        /// В МЕТОД ФИЛЬТРА ПО ПАРАМЕТРАМ!
        {
            if (hasBrand() && !hasCountry())
            {
                products = productRepo.findBySubCategoryAndBrand(subCategory, getBrand());
                resetHasBrand();
            }
            else if (hasCountry() && !hasBrand())
            {
                products = productRepo.findBySubCategoryAndCountry(subCategory, getCountry());
                resetHasCountry();
            }
            else if (hasBrand() && hasCountry())
            {
                products = productRepo.findBySubCategoryAndBrandOrSubCategoryAndCountry(subCategory, getBrand(), subCategory, getCountry());
                resetHasBrand();
                resetHasCountry();
            }
            else products = productRepo.findBySubCategory(subCategory);
        }
        return filterByPrice(products);
    }
    private List<Product> constructFinalProductList(Type type) throws NullPointerException {
        List<Product> products;
        /// В МЕТОД ФИЛЬТРА ПО ПАРАМЕТРАМ!
        {
            if (hasBrand() && !hasCountry())
            {
                products = productRepo.findByTypeAndBrand(type, getBrand());
                resetHasBrand();
            }
            else if (hasCountry() && !hasBrand())
            {
                products = productRepo.findByTypeAndCountry(type,getCountry());
                resetHasCountry();
            }
            else if (hasBrand() && hasCountry())
            {
                products = productRepo.findByTypeAndBrandOrTypeAndCountry(type, getBrand(), type, getCountry());
                resetHasBrand();
                resetHasCountry();
            }
            else products = productRepo.findByType(type);
        }
        return filterByPrice(products);
    }
    private List<Product> constructFinalProductList() throws NullPointerException {
        List<Product> products;
        /// В МЕТОД ФИЛЬТРА ПО ПАРАМЕТРАМ!
        {
            if (hasBrand() && !hasCountry())
            {
                products = productRepo.findByBrand(getBrand());
                resetHasBrand();
            }
            else if (hasCountry() && !hasBrand())
            {
                products = productRepo.findByCountry(getCountry());
                resetHasCountry();
            }
            else if (hasBrand() && hasCountry())
            {
                products = productRepo.findByBrandOrCountry(getBrand(), getCountry());
                resetHasBrand();
                resetHasCountry();
            }
            else products = productRepo.findAll();
        }
        return filterByPrice(products);
    }

    private List<Product> filterByPrice(List<Product> products) {
        if (hasSortMin() && !hasSortMax())
        {
            products = products.stream()
                    .filter(product -> (product.getPrice()) >= getSortMin())
                    .collect(Collectors.toList());
            resetHasSortMin();
            return products;
        }
        else if (hasSortMax() && !hasSortMin())
        {
            products = products.stream()
                    .filter(product -> (product.getPrice()) <= getSortMax())
                    .collect(Collectors.toList());
            resetHasSortMax();
            return products;
        }
        else if (hasSortMin() && hasSortMax())
        {
            products = products.stream()
                    .filter(product -> (product.getPrice()) >= getSortMin())
                    .filter(product -> (product.getPrice()) <= getSortMax())
                    .collect(Collectors.toList());
            resetHasSortMin();
            resetHasSortMax();
            return products;
        }
        return products;
    }

    public void collectParams(Map<String, String> filterParams, String brand, String country, String sortmin, String sortmax) {
        filterParams.put("brand", brand);
        filterParams.put("country", country);
        filterParams.put("sortmin", sortmin);
        filterParams.put("sortmax", sortmax);
        filterParams.values().removeIf(String::isEmpty);
    }

    private void getFilterParameters(Map<String, String> filterParams) {
        showParams(filterParams);
        for (Map.Entry<String, String> paramWithArg : filterParams.entrySet()) {
            String parameter = paramWithArg.getKey();
            String argument = paramWithArg.getValue();
            if (!argument.isEmpty()) {
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
                    case("sortmin"): {
                        setSortMin(Integer.parseInt(argument));
                        setHasSortMin();
                        break;
                    }
                    case("sortmax"): {
                        setSortMax(Integer.parseInt(argument));
                        setHasSortMax();
                        break;
                    }
                }
            }
        }
    }

    private void showParams(Map<String, String> filterParams) {
        System.out.println();
        for (Map.Entry<String, String> pair : filterParams.entrySet()) {
            String parameter = pair.getKey();
            String argument = pair.getValue();
            System.out.println(parameter + " " + argument);
        }
        if (filterParams.size() == 0) {
            System.out.println("No parameters!");
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

////-->X* Object howToSort;
////-->V Параметры храняться в map имя_параметра-значение
////-->V Отсеить все isEmpty параметры через стрим !!!
////-->X* Создать объект Params c соответствующими параметрами
////-->V Достать параметры из массива и применить их в поиске
////-->X* Через Params product_params = new Params();
////-->V Вынести обработчики параметров в отдельные методы
////-->V Создать логику наполнения модели отфильтрованными товарами
////    !!!СНАЧАЛА ФИЛЬТРЫ-ПАРАМЕТРЫ, ПОТОМ ЦЕНА И СОРТИРОВКА!!!
////    V1) передача параметров фильтров из формы на странице в контроллер
////    V2) распределение параметров в списки по типу
////    V3) отсев и извлечение параметров
////    V4) наполнение модели товарами с удовлетворяющими параметрами
////    V5) фильтр наполненой модели по цене
////    V6) отображение отфильтрованной модели на странице