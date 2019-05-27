package expertshop.controllers;
import expertshop.domain.User;
import expertshop.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log
@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/registration")
    public String registrationPage() {
        return "pages/registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model)
    {
        if (!userService.addUser(user))
        {
            log.info("User already exists!");
            model.put("message", "User exists!");
            return "pages/registration";
        }
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "pages/login";
    }

    @GetMapping("/cabinet")
    public String userCabinet() {
        return "pages/cabinet";
    }
}
