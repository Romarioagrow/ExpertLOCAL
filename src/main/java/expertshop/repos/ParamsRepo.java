package expertshop.repos;
import expertshop.domain.ProductParams;
import expertshop.domain.categories.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParamsRepo extends JpaRepository<ProductParams, Long> {

    List<ProductParams> findByTypeAndResolutionType(Type type, String resol);
}
