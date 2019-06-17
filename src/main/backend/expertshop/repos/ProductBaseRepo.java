package expertshop.repos;

import expertshop.domain.Product;
import expertshop.domain.ProductBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBaseRepo extends JpaRepository<ProductBase, String> {
    ProductBase findFirstByModelNameContaining(String modelName);
}
