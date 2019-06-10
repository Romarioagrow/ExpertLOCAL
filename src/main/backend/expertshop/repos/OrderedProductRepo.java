package expertshop.repos;

import expertshop.domain.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface OrderedProductRepo extends JpaRepository<OrderedProduct, Integer> {
    OrderedProduct findByid(Integer id);

    OrderedProduct findByProductID(Integer productID);

    OrderedProduct findByIdAndProductID(Integer orderedID, Integer productID);

    //Map<String, String>
}
