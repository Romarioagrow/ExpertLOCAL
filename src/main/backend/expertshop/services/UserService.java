package expertshop.services;

import expertshop.domain.User;
import expertshop.domain.categories.Role;
import expertshop.repos.UserRepo;
import lombok.AllArgsConstructor;

import lombok.extern.java.Log;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Log
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    //private final PasswordEncoder passwordEncoder;

    public boolean addUser(User user) {
        User userDB = userRepo.findByUsername(user.getUsername());
        LocalDateTime now = LocalDateTime.now();

        if (userDB != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setRegistrationDate(now);

        userRepo.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    /*@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException(email);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        //return userRepo.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }*/

    /*public boolean userRegistration(User user, Map<String, String> userDetails) {
        String regEmail = userDetails.get("email");
        if (regEmail.isEmpty())
            return false;
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
        //user.setPassword(passwordEncoder.encode(userDetails.get("password")));
        user.setEmail(userDetails.get("email"));
        user.setFullName(userDetails.get("fullName"));
        user.setMobile(userDetails.get("mobile"));
        user.setRegistrationDate(LocalDateTime.now());

        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setActive(true);
        userRepo.save(user);
        return true;
    }*/

    public void oAuthRegistration() {
        //user.setUserPic(userDetails.get("picture"));
        //user.setFullName(userDetails.get("userFullName"));
        //user.setActive(true);
        //user.setRoles(Collections.singleton(Role.ADMIN));
        //LocalDateTime now = LocalDateTime.now();
        //user.setRegistrationDate(now);
    }
}