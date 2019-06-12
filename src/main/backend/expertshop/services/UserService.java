package expertshop.services;

import expertshop.domain.User;
import expertshop.domain.categories.Role;
import expertshop.repos.UserRepo;
import lombok.AllArgsConstructor;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    public boolean registerUser(User user)
    {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            return false;
        }

        user.setActive(true);
        user.setRegistrationDate(LocalDateTime.now());
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        log.info("User " + user.getUsername() + " successfully registered!");
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}