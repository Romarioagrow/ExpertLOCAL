package expertshop.services;

import expertshop.domain.User;
import expertshop.repos.UserRepo;
import lombok.AllArgsConstructor;

import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Log
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) userRepo.findByEmail(email);
    }

    public boolean addUser(User user, Map<String, String> userDetails) {
        String regEmail = userDetails.get("email");
        log.info("Registration email " + regEmail);

        if (userRepo.findByEmail(regEmail) != null) {
            log.info("User exists! " + regEmail);
            return false;
        }
        if (!userDetails.get("password").equals(userDetails.get("passwordConfirm"))) {
            log.info("Passwords are not same!");
            return false;
        }

        log.info("No user, creating new one");
        user.setUserID(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(userDetails.get("password")));
        user.setEmail(userDetails.get("email"));
        user.setFullName(userDetails.get("fullName"));
        user.setMobile(userDetails.get("mobile"));
        user.setRegistrationDate(LocalDateTime.now());

        userRepo.save(user);
        return true;
    }

    public void oAuthRegistration() {
        //user.setUserPic(userDetails.get("picture"));
        //user.setFullName(userDetails.get("userFullName"));
        //user.setActive(true);
        //user.setRoles(Collections.singleton(Role.ADMIN));
        //LocalDateTime now = LocalDateTime.now();
        //user.setRegistrationDate(now);
    }
}

