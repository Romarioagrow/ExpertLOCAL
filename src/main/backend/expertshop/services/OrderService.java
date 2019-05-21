package expertshop.services;
import expertshop.domain.Order;
import expertshop.repos.OrderRepo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Log
@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;

    public Order getCurrentOrder() {
        return orderRepo.findBySessionUUID(RequestContextHolder.currentRequestAttributes().getSessionId());
    }
}
