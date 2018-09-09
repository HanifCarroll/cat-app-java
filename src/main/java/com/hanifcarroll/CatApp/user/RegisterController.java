package com.hanifcarroll.CatApp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public String processRegistrationForm(String username, String email, String password) {
        try {
            User oldEmail = userService.findByEmail(email);

            if (!(oldEmail == null)) return "Account with that email already exists.";

            User oldUsername = userService.findByUsername(username);
            if (!(oldUsername == null)) return "Account with that username already exists.";

            userService.registerUser(username, email, password);

            return "User successfully registered";
        } catch (Exception e) {
            return "Error registering the user: " + e;
        }
    }
}
