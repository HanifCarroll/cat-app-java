package com.hanifcarroll.CatApp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Iterable<User> listAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        try {
            userService.deleteById(id);

            return "User successfully deleted.";
        } catch (Exception e) {
            return "Error deleting the user: " + e;
        }
    }

    @PatchMapping("/{id}")
    public String updateCat(
            @PathVariable long id,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        try {
            userService.updateUser(id, username, email, password);
            return "User successfully updated.";
        } catch (Exception e) {
            return "Error updating user: " + e;
        }
    }
}
