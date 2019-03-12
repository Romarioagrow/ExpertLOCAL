package expertshop.services;
import org.springframework.stereotype.Service;

@Service
public class FilterService {
    public void filter(String ... params) {
        for (String param : params) {
            System.out.println(param);
        }
    }
}
