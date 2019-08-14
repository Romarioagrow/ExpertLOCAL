package expertshop.services;

import expertshop.domain.Order;
import expertshop.domain.User;
import expertshop.domain.categories.Role;
import expertshop.repos.OrderRepo;
import expertshop.repos.UserRepo;
import lombok.AllArgsConstructor;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.*;

@Log
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    /// ПОВТОРНАЯ ОТПРАВКА/ВЫВОД ВВЕДЕННЫХ ПОЛЕЙ ФОРМЫ!!!
    public boolean registerUser(User user, String sessionID, String passwordConfirm, Model model)
    {
        if (userRepo.findByUsername(user.getUsername()) != null)
        {
            log.info("User already exists!");
            model.addAttribute("message", "Пользователь уже существует!");
            return false;
        }

        if (!user.getPassword().equals(passwordConfirm))
        {
            log.info("Passwords Match Error!");
            model.addAttribute("message", "Пароли не совпадают!");
            return false;
        }

        user.setActive(true);
        user.setRegistrationDate(LocalDateTime.now());
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        /// Обрезка случайных пробелов в ФИО
        user.setFirstName(user.getFirstName().trim());
        user.setLastName(user.getLastName().trim());
        user.setOtchestvo(user.getOtchestvo().trim());
        userRepo.save(user);

        Order userOrder =  orderRepo.findBySessionUUIDAndAcceptedFalse(sessionID);
        if (userOrder != null) {
            userOrder.setUserID(user.getUserID());
            orderRepo.save(userOrder);
        }

        log.info("User " + user.getUsername() + " successfully registered!");
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        /// Присовение закзаза сессии пользователю при логине
        if (!user.getRoles().toString().contains("ADMIN"))
        {
            String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
            Order order = orderRepo.findBySessionUUIDAndAcceptedFalse(sessionID);
            if (order == null) return user;
            order.setUserID(user.getUserID());
            orderRepo.save(order);
        }
        return user;
    }
}