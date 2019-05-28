package expertshop.repos;
import expertshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    User findByUserID(Long userID);

    User findByUsername(String username);
}