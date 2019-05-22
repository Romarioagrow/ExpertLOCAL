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
    /*@PostMapping("/registration")
    public String addUser(@RequestBody Map<String, String> userDetails, User user, Model model)
    {
        log.info("Registration controller");
        if (!userService.userRegistration(user, userDetails))
        {
            log.info("return");
            model.addAttribute("message", userDetails.get("email"));
            return "pages/login";
        }
        log.info("Registration OK");
        return "pages/order";
    }*/

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
        log.info("Login page");
        return "pages/login";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        log.info("Registration page");
        return "pages/registration";
    }

    @GetMapping("/cabinet")
    public String userCabinet() {
        log.info("userCabinet page");
        return "pages/cabinet";
    }
}
