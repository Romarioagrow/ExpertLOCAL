package expertshop.repos;
import expertshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    Order findByOrderID(Long orderID);
    Order findBySessionUUIDAndAcceptedFalse(String sessionID);
    Order findFirstByUserIDAndAcceptedFalse(Long userID);

    Set<Order> findOrdersByUserIDAndAcceptedTrue(Long userID);

    List<Order> findAllByAcceptedTrue();
    List<Order> findAllByCompletedTrue();
    List<Order> findByShortTelAndAcceptedTrue(String tel);
    List<Order> findAllByUserIDAndCompletedTrue(Long userID);
}
