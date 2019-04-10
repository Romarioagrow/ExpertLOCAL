package expertshop.controllers;
import expertshop.domain.Product;
import expertshop.domain.categories.Type;
import expertshop.repos.ProductRepo;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import java.util.*;

@Log
@RestController
@AllArgsConstructor
public class FilterController {
    private final ProductRepo productRepo;

    @PostMapping("/tv")
    public List<Map<String, String>> test(@RequestBody Map<String, String> params) {
        for (Map.Entry entry : params.entrySet()) {
            log.info(entry.getKey() + ", " + entry.getValue());
        }

        //findByType(requiredType)
        List<Product> products = productRepo.findByType(Type.tv);
        
        List<Map<String, String>> response = new ArrayList<>();
        int i = 0;
        for (Product product1 : products )
        {
            Map<String, String> product = new HashMap<>();
            product.put("Brand", product1.getBrand());
            product.put("Model", product1.getModel());
            product.put("Price", String.valueOf(product1.getPrice()));

            response.add(i, product);
            i++;
        }
        return response;
    }
}
    /*List<String[]> productsString = new ArrayList<>();

        for (Product product : products) {
            int i = 0;
            productsString.set(i, new String[]{
                    product.getBrand(),
                    product.getModel(),
                    product.getCategory().toString(),
                    product.getType().toString()
            });
        }*/

        /*ObjectMapper mapper = new ObjectMapper();
        Collections.singletonList(mapper.writeValueAsString(product))*/

        /*return products_list.stream()
                .filter(product -> product.getProduct_id() > 0)
                .collect(Collectors.toMap(Product::getModel, Product::getModel));*/


    /*@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable("id") long id){
        return employeeService.getEmployeeById(id);
    }*/


    /*@PostMapping("/tv")
    public void getProductsViaAjax(
            @RequestBody String [] params)
    {
        for (String s : params) {
            System.out.println(s);
        }

        JsonParser jsonParser;

        jsonParser.parseList();

        List pp = params.parseList()

        Map pars = params.parseMap(String.valueOf(params));

        System.out.println(lol);
    }*/

