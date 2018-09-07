package com.hanifcarroll.CatApp.cat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hanifcarroll.CatApp.rating.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "cats")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String pictureURL;

//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cat")
    @JsonIgnoreProperties("cat")
    private List<Rating> ratings;

    private double averageRating;

    public Cat() {
    }

    public Cat(String name, String description, String pictureURL) {
        this.name = name;
        this.description = description;
        this.pictureURL = pictureURL;
    }

    public Cat(String name, String description, String pictureURL, List<Rating> ratings) {
        this.name = name;
        this.description = description;
        this.pictureURL = pictureURL;
        this.ratings = ratings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public double getAverageRating() {
        return this.averageRating;
    }

    @PostLoad
    public void setAverageRating() {
        double sum = 0.0;
        for (Rating rating : this.ratings) {
            sum += (double) rating.getValue();
        }

        double average = sum / (double) this.ratings.size();

       this.averageRating =  (double) Math.round(average * 100d) / 100d;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                ", averageRating" + averageRating + '\'' +
                '}';
    }
}
