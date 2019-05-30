package expertshop.repos;
import expertshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    Order findBySessionUUID(String sessionID);

    Order findBySessionUUIDAndAcceptedFalse(String sessionID);

    Order findByOrderID(Integer orderID);

    Set<Order> findOrdersByUserID(Long userID);

    Order findByUserIDAndAcceptedFalse(Long userID);
}
