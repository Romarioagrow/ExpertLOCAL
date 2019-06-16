package expertshop.repos;
import expertshop.domain.Product;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.domain.categories.Type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);

    List<Product> findBySubCategory(String subCategory);

    List<Product> findByType(String type);

    Product findByProductID(String ID);

    List<Product> findBySubCategoryContains(String productRequest);

    List<Product> findByNameContains(String name);

    List<Product> findBySubCategoryContainsOrProductGroupContains(String subCategory, String ProductGroup);

    List<Product> findByProductGroupContains(String productRequest);

    Set<Product> findByProductGroupContainingIgnoreCase(String productRequest);

    Set<Product> findByTypeContainingIgnoreCase(String productRequest);
}
