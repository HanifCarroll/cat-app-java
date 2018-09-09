package com.hanifcarroll.CatApp.cat;

import com.hanifcarroll.CatApp.user.User;
import com.hanifcarroll.CatApp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cats")
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public Iterable<Cat> listAll() {
        return catRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Cat> findById(@PathVariable long id) {
        return catRepository.findById(id);
    }

    @PostMapping("/")
    public String create(String name, String description, String pictureURL, long ownerId) {
        String catId = "";

        try {
            Optional<User> owner = userRepository.findById(ownerId);

            if (!owner.isPresent()) {
                return "User with that ID not found.";
            }

            User foundOwner = owner.get();
            Cat cat = new Cat(name, description, pictureURL, foundOwner);

            catRepository.save(cat);

            catId = String.valueOf(cat.getId());

            return cat.toString();
        } catch (Exception e) {
            return "Error creating the cat: " + e;
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        try {
            catRepository.deleteById(id);

            return "Cat successfully deleted.";
        } catch (Exception e) {
            return "Error deleting the cat: " + e;
        }
    }

    @PatchMapping("/{id}")
    public String updateCat(
            @PathVariable long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("pictureURL") String pictureURL
    ) {
        try {
            catRepository.updateCat(id, name, description, pictureURL);
            return "Cat successfully updated.";
        } catch (Exception e) {
            return "Error updating cat: " + e;
        }
    }




}
