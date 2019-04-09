package expertshop.repos;
import expertshop.domain.Product;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.domain.categories.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);

    List<Product> findBySubCategory(SubCategory subCategory);

    List<Product> findByType (Type type);
}
