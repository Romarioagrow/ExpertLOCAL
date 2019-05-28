package expertshop.repos;
import expertshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    Order findBySessionUUID(String sessionID);

    Order findByOrderID(Integer orderID);

    Order findByUserID(Long userID);

    Order findByUserIDAndAcceptedFalse(Long userID);
}
