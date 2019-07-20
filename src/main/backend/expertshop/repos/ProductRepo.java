package expertshop.repos;
import expertshop.domain.Product;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByProductID(String ID);

    /*Page<Product> findByOriginalTypeContainingOrOriginalGroupContainingOrOriginalNameContaining(String type, String group, String name, Pageable pageable);*/

    Page<Product> findByOriginalTypeContainingOrOriginalNameContaining(String type, String name, Pageable pageable);
    Page<Product> findByProductGroupEqualsIgnoreCase(String productGroup, Pageable pageable);
    Page<Product> findByProductGroupEqualsIgnoreCaseAndSupplier(String productGroup, String supplier, Pageable pageable);

    //List<Product> findAllBySupplierAndProductGroupIsNotNull
    List<Product> findByProductCategoryAndSupplierAndProductGroupIsNotNull(String cat, String supp);
    List<Product> findByProductGroup(String group);
    List<Product> findByProductGroupAndSupplier(String group, String sup);
    List<Product> findByShortNameStartingWith(String s);
    List<Product> findByShortModelEquals(String s);
    List<Product> findByShortModelEqualsAndProductGroupIsNotNull(String s);

    List<Product> findAllByProductGroupIsNull();
    List<Product> findAllByProductGroupIsNotNull();
    List<Product> findAllByProductGroupIsNotNullAndIsDuplicateIsNull();

    List<Product> findProductsByProductGroupEqualsIgnoreCase(String productGroup);
    List<Product> findBySupplier(String supplier);
    List<Product> findBySupplierAndProductGroupIsNotNull(String supplier);
    List<Product> findProductsByProductGroupEqualsIgnoreCaseAndSupplier(String productGroup, String supplier);
    List<Product> findByModelNameIgnoreCaseOrOriginalNameIgnoreCase(String modelName, String origName);
    List<Product> findAllByModelNameNotNull();
    List<Product> findByModelNameStartsWithIgnoreCase(String modelName);

    /*List<Product> findByOriginalGroup(String group);*/


    /*List<Product> findByCategory(String category);
    List<Product> findBySubCategory(String subCategory);
    List<Product> findBySupplier(String supplier);
    List<Product> findByType(String type);

    Page<Product> findByProductGroupContainingIgnoreCaseOrTypeContainingIgnoreCaseOrFullNameContainingIgnoreCase(String s, String s1, String s3, Pageable pageable);
    Set<Product> findProductsSetByProductGroupContainingIgnoreCaseOrTypeContainingIgnoreCaseOrFullNameContainingIgnoreCase(String s, String s1, String s3);
    List<Product> findProductsListByProductGroupContainingIgnoreCaseOrTypeContainingIgnoreCaseOrFullNameContainingIgnoreCase(String s, String s1, String s3);

    Page<Product> findByProductGroupContainsIgnoreCase(String productRequest, Pageable pageable);
    Page<Product> findByProductGroupContainingIgnoreCase(String productRequest, Pageable pageable);
    Page<Product> findByTypeContainsIgnoreCase(String productRequest, Pageable pageable);
    Page<Product> findByTypeContainingIgnoreCase(String productRequest, Pageable pageable);
    Page<Product> findByFullNameContainsIgnoreCase(String productRequest, Pageable pageable);
    Page<Product> findByFullNameContainingIgnoreCase(String productRequest, Pageable pageable);

    Set<Product> findByProductGroupContainsIgnoreCase(String productRequest, Pageable pageable);
    Set<Product> findByProductGroupContainingIgnoreCase(String productRequest, Pageable pageable);
    Set<Product> findByTypeContainsIgnoreCase(String productRequest, Pageable pageable);
    Set<Product> findByTypeContainingIgnoreCase(String productRequest, Pageable pageable);
    Set<Product> findByFullNameContainsIgnoreCase(String productRequest, Pageable pageable);
    Set<Product> findByFullNameContainingIgnoreCase(String productRequest, Pageable pageable);
    */
}
