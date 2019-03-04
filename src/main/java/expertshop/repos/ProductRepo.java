package expertshop.repos;
import expertshop.entities.Categories;
import expertshop.entities.Product;
import expertshop.entities.Types;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Categories category);

    List<Product> findByCategoryAndType(Categories category, Types type);
}
