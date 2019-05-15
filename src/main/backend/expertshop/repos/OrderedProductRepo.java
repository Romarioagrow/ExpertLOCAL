package expertshop.repos;

import expertshop.domain.OrderedProduct;
import expertshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductRepo extends JpaRepository<OrderedProduct, Integer> {
    OrderedProduct findByProductID(Integer productID);

    OrderedProduct findByIdAndProductID(Integer orderedID, Integer productID);
}
