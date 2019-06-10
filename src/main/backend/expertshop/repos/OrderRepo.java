package expertshop.repos;
import expertshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    Set<Order> findBySessionUUID(String sessionID);

    Set<Order> findOrdersByUserID(Long userID);

    Order findBySessionUUIDAndAcceptedFalse(String sessionID);

    Order findByOrderID(Integer orderID);

    Order findByUserIDAndAcceptedFalse(Long userID);

    //Optional<Order> findAllByUserIDAndAcceptedFalse(Long userID);
}
