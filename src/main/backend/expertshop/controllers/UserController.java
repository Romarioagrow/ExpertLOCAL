package expertshop.controllers;
import expertshop.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

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
