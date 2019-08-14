package expertshop.repos;
import expertshop.domain.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByProductID(String ID);
    List<Product> findBySupplierAndOriginalPicIsNullAndLinkRIsNotNull(String supp);

    List<Product> findByOriginalCategoryStartsWith(String category);
    List<Product> findByProductGroupIsNullAndSupplierEqualsAndOriginalCategoryContains(String supplier, String category);

    Page<Product> findByOriginalTypeAndIsAvailableIsTrue(String req, Pageable pageable);

    /*Page<Product> findByOriginalTypeContainingOrOriginalGroupContainingOrOriginalNameContaining(String type, String group, String name, Pageable pageable);*/

    Page<Product> findByOriginalTypeContainingOrOriginalNameContaining(String type, String name, Pageable pageable);

    Page<Product> findByProductGroupEqualsIgnoreCase(String productGroup, Pageable pageable);
    Page<Product> findByProductGroupEqualsIgnoreCaseAndIsAvailableTrueAndOriginalPicIsNotNull(String productGroup, Pageable pageable);

    Page<Product> findByProductGroupEqualsIgnoreCaseAndSupplier(String productGroup, String supplier, Pageable pageable);

    Page<Product> findProductsByProductGroupEqualsIgnoreCase(String s, Pageable pageable);
    Page<Product> findByProductGroupEqualsIgnoreCaseAndIsAvailableTrue(String s, Pageable pageable);

    //List<Product> findAllBySupplierAndProductGroupIsNotNull
    List<Product> findByProductGroupEqualsIgnoreCase(String group);
    List<Product> findBySupplierAndProductGroupIsNotNullAndOriginalPicIsNullAndLinkRIsNotNull(String sup);



    /*ADMIN*/
    List<Product> findByProductCategoryEqualsIgnoreCase(String category);
    List<Product> findByShortSearchNameContains(String shortName);
    List<Product> findBySupplierContainsIgnoreCase(String supp);
    List<Product> findBySupplierContainsIgnoreCaseAndProductGroupIsNotNull(String supp);
    List<Product> findByProductGroupNotNullAndOriginalBrandContainsIgnoreCase(String brand);

    List<Product> findByProductGroupContainsIgnoreCase(String request);

    List<Product> findAllByProductGroupNotNullAndOriginalPicNotNull();

    List<Product> findProductsByProductGroupEqualsIgnoreCase(String s);
    List<Product> findByProductCategoryAndSupplierAndProductGroupIsNotNull(String cat, String supp);
    List<Product> findByProductGroup(String group);
    List<Product> findByProductGroupAndSupplier(String group, String sup);
    List<Product> findByShortModelEquals(String s);
    List<Product> findByShortModelEqualsAndProductGroupIsNotNull(String s);

    List<Product> findByLinkRIsNotNull();
    List<Product> findAllByModelNameNotNullAndOriginalPicIsNull();

    List<Product> findProductsByProductGroupEqualsIgnoreCaseAndIsDuplicateIsNullAndIsAvailableTrue(String req);
    Page<Product> findProductsByProductGroupEqualsIgnoreCaseAndIsDuplicateIsNullAndIsAvailableTrue(String req, Pageable pageable);
    Page<Product> findProductsByProductGroupEqualsIgnoreCaseAndIsAvailableTrue(String req, Pageable pageable);
    List<Product> findProductsByProductGroupEqualsIgnoreCaseAndIsAvailableTrue(String req);

    List<Product> findAllByProductGroupIsNull();
    List<Product> findAllByProductGroupIsNotNull();
    List<Product> findAllByProductGroupIsNotNullAndIsDuplicateIsNull();
    List<Product> findAllByProductGroupIsNotNullAndIsDuplicateIsNullAndShortSearchNameContainsIgnoreCase(String search);

    //List<Product> findAllByProductGroupIsNotNullAndIsAvailableTrueAndIsDuplicateIsNullAndShortSearchNameContainsIgnoreCase(String search);

    //List<Product> findProductsByProductGroupEqualsIgnoreCase(String productGroup);
    List<Product> findBySupplier(String supplier);
    List<Product> findBySupplierAndProductGroupIsNotNull(String supplier);
    List<Product> findProductsByProductGroupEqualsIgnoreCaseAndSupplier(String productGroup, String supplier);
    List<Product> findByModelNameIgnoreCaseOrOriginalNameIgnoreCase(String modelName, String origName);

    List<Product> findAllByModelNameNotNull();
    //List<Product> findAllByModelNameNotNullAndOriginalPicIsNull();

    List<Product> findAllByProductGroupNotNullAndFinalPriceIsNull();

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
