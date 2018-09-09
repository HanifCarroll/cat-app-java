package com.hanifcarroll.CatApp.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public String processRegistrationForm(String username, String email, String password) {
        try {
            User userExists = userService.findByEmail(email);

            if (userExists != null) return "User with that email already exists!";

            String encryptedPassword = bCryptPasswordEncoder.encode(password);

            userService.save(new User(username, email, encryptedPassword));

            return "User successfully registered";
        } catch (Exception e) {
            return "Error registering the user: " + e;
        }
    }
}
