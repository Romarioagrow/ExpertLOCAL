package expertshop.services;
import expertshop.entities.Product;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SortService {
    public void sorted(List<Product> products, String sortby) {
        if (sortby != null && !sortby.isEmpty()) { /// В SORT SERVICE
            switch (sortby) {
                case "lowest":
                    products.sort(Comparator.comparing(Product::getPrice));
                    break;
                case "highest":
                    products.sort(Comparator.comparing(Product::getPrice));
                    Collections.reverse(products);
                    break;
                //case "n":
            }
        }
    }
}
