package expertshop.repos;
import expertshop.domain.ProductWrap;
import expertshop.domain.categories.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WrapRepo extends JpaRepository<ProductWrap, Long> {

    List<ProductWrap> findByType (Type type);
}
