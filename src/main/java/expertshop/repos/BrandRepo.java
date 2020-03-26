package expertshop.repos;
import expertshop.domain.BrandProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends JpaRepository<BrandProduct, String> {
    BrandProduct findByProductID(String id);
    BrandProduct findByShortModel(String shortModel);
}
