package expertshop.services;

import expertshop.domain.User;
import expertshop.domain.categories.Role;
import expertshop.repos.UserRepo;
import lombok.AllArgsConstructor;

import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Log
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    //private final PasswordEncoder passwordEncoder;

    public boolean addUser(User user)
    {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setRegistrationDate(LocalDateTime.now());

        userRepo.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}