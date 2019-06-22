package expertshop.repos;
import expertshop.domain.Product;
import expertshop.domain.ProductBase;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.domain.categories.Type;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByProductID(String ID);
    //Product findFirstByModelNameContaining(String modelName);


    /*List<Product> findByCategory(String category);
    List<Product> findBySubCategory(String subCategory);
    List<Product> findBySupplier(String supplier);
    List<Product> findByType(String type);

    Page<Product> findByProductGroupContainingIgnoreCaseOrTypeContainingIgnoreCaseOrFullNameContainingIgnoreCase(String s, String s1, String s3, Pageable pageable);

    Set<Product> findProductsSetByProductGroupContainingIgnoreCaseOrTypeContainingIgnoreCaseOrFullNameContainingIgnoreCase(String s, String s1, String s3);

    List<Product> findProductsListByProductGroupContainingIgnoreCaseOrTypeContainingIgnoreCaseOrFullNameContainingIgnoreCase(String s, String s1, String s3);*/
    /*
    Page<Product> findByProductGroupContainsIgnoreCase(String productRequest, Pageable pageable);
    Page<Product> findByProductGroupContainingIgnoreCase(String productRequest, Pageable pageable);
    Page<Product> findByTypeContainsIgnoreCase(String productRequest, Pageable pageable);
    Page<Product> findByTypeContainingIgnoreCase(String productRequest, Pageable pageable);
    Page<Product> findByFullNameContainsIgnoreCase(String productRequest, Pageable pageable);
    Page<Product> findByFullNameContainingIgnoreCase(String productRequest, Pageable pageable);
    */

    /*
    Set<Product> findByProductGroupContainsIgnoreCase(String productRequest, Pageable pageable);
    Set<Product> findByProductGroupContainingIgnoreCase(String productRequest, Pageable pageable);
    Set<Product> findByTypeContainsIgnoreCase(String productRequest, Pageable pageable);
    Set<Product> findByTypeContainingIgnoreCase(String productRequest, Pageable pageable);
    Set<Product> findByFullNameContainsIgnoreCase(String productRequest, Pageable pageable);
    Set<Product> findByFullNameContainingIgnoreCase(String productRequest, Pageable pageable);
    */


}
