package expertshop.services;
import expertshop.domain.Product;
import expertshop.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FilterService {
    @Autowired
    ProductRepo productRepo;

    public void filterResolver (List<Product> products, Map<String, String> filterParams) {
        /*List<String> filtered = new ArrayList<>();*/

        /*
        String value = pair.getValue();
        String val = value.getObjectNAME();
        String key = pair.getKey();
        */

        showParams(filterParams);

        // Отфильтровать пустые параментры
        filterParams.values().removeIf(String::isEmpty);

        showParams(filterParams);

        /*for (Map.Entry<String, String> pair : filterParams.entrySet()) {

            String parameter = pair.getKey();
            String argument = pair.getValue();

            System.out.println(parameter + " " + argument);

            --> отсеить все "" и null параметры через стрим !!!
            filterParams.values().removeIf(String::isEmpty);

            System.out.println(parameter + " " + argument);

        }

        filterParams.values().removeIf(String::isEmpty);

        for (Map.Entry<String, String> pair : filterParams.entrySet()) {

            ////--> отсеить все "" и null параметры через стрим !!!

            String parameter = pair.getKey();
            String argument = pair.getValue();

            System.out.println(parameter + " " + argument);

        }*/
            /*System.out.println(param);
            if (param != null) {
                //new Params(param);
                filtered.add(param);
            }*/


        /*for (String param : filterParams) {
            System.out.println(param);
            if (param != null) {
                *//*new Params(param);*//*
                filtered.add(param);
            }
            // Проитерировать элементы filterParams
            // Если эллемент не равен null добавить в список применяемых фильтров
            // МЕТОД БД: найти все сущности категории электроника c совпадающими параметрами
        }*/

       /* String[] toFilter = new String[filterParams.size()];

        filtered.toArray(toFilter);

        products = productRepo.findProductByCategoryAndParameters(Categories.Electronics, toFilter);

        return products;*/

        /// должен разделять фильтра и
        /// параметра храняться в мап имя_параметра-значение
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
