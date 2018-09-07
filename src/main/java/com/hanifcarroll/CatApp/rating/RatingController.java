package com.hanifcarroll.CatApp.rating;

import com.hanifcarroll.CatApp.cat.Cat;
import com.hanifcarroll.CatApp.cat.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private CatRepository catRepository;

    @PostMapping("")
    public String create(@RequestParam("catId") long catId, @RequestParam("value") int value) {
        String ratingId = "";

        try {
            Optional<Cat> cat = catRepository.findById(catId);

            if (!cat.isPresent()) {
                return "Cat with that ID not found.";
            }

            Cat foundCat = cat.get();

            Rating rating = new Rating(foundCat, value);

            System.out.println(rating);

            ratingRepository.save(rating);

            ratingId = String.valueOf(rating.getId());

            return rating.toString();
        } catch (Exception e) {
            return "Error creating the rating: " + e;
        }
    }
}
