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

    /*List<Product> findAllByParameters(String parameters);*/
}
