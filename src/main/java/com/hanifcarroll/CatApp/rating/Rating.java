package com.hanifcarroll.CatApp.rating;

import com.fasterxml.jackson.annotation.*;
import com.hanifcarroll.CatApp.cat.Cat;
import com.hanifcarroll.CatApp.user.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ratings")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER_ID"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( foreignKey = @ForeignKey(name = "FK_CAT_ID"))
    private Cat cat;

    @NotNull
    @Min(1)
    @Max(5)
    private int value;

    public Rating() {}

    public Rating(Cat cat, int value) {
        this.cat = cat;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", user=" + user +
                ", cat=" + cat +
                ", value=" + value +
                '}';
    }
}
