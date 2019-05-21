package expertshop.controllers;

import expertshop.domain.User;
import expertshop.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Log
@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


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
    /*@PostMapping("/registration")
    public String addUser(@RequestBody Map<String, String> userDetails, User user, Model model)
    {
        log.info("Registration controller");
        if (!userService.addUser(user, userDetails))
        {
            log.info("return");
            model.addAttribute("message", userDetails.get("email"));
            return "pages/registration";
        }
        log.info("Registration OK");
        return "redirect:/user/login";
    }*/

    @GetMapping("/cabinet")
    public String userCabinet() {
        log.info("userCabinet page");
        return "pages/cabinet";
    }
}
