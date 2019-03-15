package expertshop.repos;
import expertshop.domain.Params;
import expertshop.domain.Product;
import expertshop.domain.lists.Categories;
import expertshop.domain.lists.Types;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Categories category);

    List<Product> findByType(Types type);

    List<Product> findByParameters(Params parameters);

    /*List<Product> findAllByParameters(String parameters);*/

    List<Product> findProductByCategoryAndParameters(Categories category, Params parameters);

    List<Product> findByCategoryAndBrand(Categories category, String brand);

    List<Product> findByCategoryAndCountry(Categories category, String country);

}
