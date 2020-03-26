package expertshop.repos;
import expertshop.domain.ProductBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepo extends JpaRepository<ProductBase, String> {
    ProductBase findFirstByShortModelEquals(String shortModel);
}
