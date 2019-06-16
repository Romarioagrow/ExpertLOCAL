package expertshop.repos;

import expertshop.domain.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductRepo extends JpaRepository<OrderedProduct, Integer> {
    OrderedProduct findByOrderedID(String id);

    OrderedProduct findByProductID(String productID);

    OrderedProduct findByOrderedIDAndProductID(String orderedID, String productID);

    //Map<String, String>
}
