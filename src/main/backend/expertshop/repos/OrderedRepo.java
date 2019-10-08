package expertshop.repos;
import expertshop.domain.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepo extends JpaRepository<OrderedProduct, Integer> {
    OrderedProduct findByOrderedID(Long orderedID);
}
