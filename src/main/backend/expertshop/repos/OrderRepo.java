package expertshop.repos;
import expertshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    Set<Order> findBySessionUUID(String sessionID);

    Set<Order> findOrdersByUserIDAndAcceptedTrue(Long userID);

    Order findBySessionUUIDAndAcceptedFalse(String sessionID);

    Order findByOrderID(Long orderID);

    Order findByUserIDAndAcceptedFalse(Long userID);

    List<Order> findAllByAcceptedTrue();

    List<Order> findByShortTelAndAcceptedTrue(String tel);

    List<Order> findAllByCompletedTrue();

    List<Order> findAllByUserIDAndCompletedTrue(Long userID);

    //Optional<Order> findAllByUserIDAndAcceptedFalse(Long userID);
}
