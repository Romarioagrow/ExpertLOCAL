package expertshop.services;
import expertshop.domain.Product;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Deprecated
public class SortService {
    public void sorted(List<Product> products, String sortby) {
        if (sortby != null && !sortby.isEmpty()) {
            switch (sortby) {
                case "lowest":
                    products.sort(Comparator.comparing(Product::getPrice));
                    break;
                case "highest":
                    products.sort(Comparator.comparing(Product::getPrice));
                    Collections.reverse(products);
                    break;
            }
        }
    }
}
