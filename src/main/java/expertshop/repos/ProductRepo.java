package expertshop.repos;
import expertshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByProductID(String ID);
    Product findFirstByOriginalTypeAndOriginalPicIsNotNull(String type);

    List<Product> findByFinalPriceIsNull();
    List<Product> findByProductGroupEqualsIgnoreCase(String group);
    List<Product> findByOriginalNameContainsIgnoreCase(String name);
    List<Product> findByOriginalTypeIgnoreCaseAndIsAvailableIsTrue(String req);
    List<Product> findBySupplierAndOriginalPicIsNullAndLinkRIsNotNull(String supp);
    List<Product> findByOriginalNameContainsIgnoreCaseAndIsAvailableTrue(String request);
    List<Product> findByProductGroupIsNullAndSupplierEqualsAndOriginalCategoryContains(String supplier, String category);
    List<Product> findByOriginalTypeContainsIgnoreCaseAndIsAvailableTrueAndFinalPriceIsNotNull(String request);

    Page<Product> findByOriginalTypeAndIsAvailableIsTrue(String req, Pageable pageable);
    Page<Product> findProductsByProductGroupEqualsIgnoreCaseAndIsDuplicateIsNullAndIsAvailableTrue(String req, Pageable pageable);

    /*ADMIN*/
    List<Product> findBySupplier(String supplier);
    List<Product> findByShortModelEquals(String s);
    List<Product> findByShortSearchNameContains(String shortName);
    List<Product> findBySupplierContainsIgnoreCase(String supp);
    List<Product> findByProductCategoryEqualsIgnoreCase(String category);
    List<Product> findBySupplierAndProductGroupIsNotNull(String supplier);
    List<Product> findBySupplierContainsIgnoreCaseAndProductGroupIsNotNull(String supp);
    List<Product> findByProductGroupNotNullAndOriginalBrandContainsIgnoreCase(String brand);
    List<Product> findProductsByProductGroupEqualsIgnoreCaseAndIsAvailableTrue(String req);
    List<Product> findProductsByProductGroupEqualsIgnoreCaseAndIsDuplicateIsNullAndIsAvailableTrue(String req);

    List<Product> findAllByModelNameNotNull();
    List<Product> findAllByProductGroupIsNull();
    List<Product> findAllByProductGroupIsNotNull();
    List<Product> findAllByModelNameNotNullAndOriginalPicIsNull();
    List<Product> findAllByProductGroupNotNullAndFinalPriceIsNull();
    List<Product> findAllByProductGroupNotNullAndOriginalPicNotNull();
    List<Product> findAllByProductGroupIsNotNullAndIsDuplicateIsNullAndShortSearchNameContainsIgnoreCase(String search);
}
