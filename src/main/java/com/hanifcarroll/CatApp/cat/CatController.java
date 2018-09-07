package com.hanifcarroll.CatApp.cat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/cats")
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @GetMapping("")
    public Iterable<Cat> listAll() {
        return catRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Cat> findById(@PathVariable long id) {
        return catRepository.findById(id);
    }

    @PostMapping("")
    public String create(String name, String description, String pictureURL) {
        String catId = "";

        try {
            Cat cat = new Cat(name, description, pictureURL);

            System.out.println(cat);

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
