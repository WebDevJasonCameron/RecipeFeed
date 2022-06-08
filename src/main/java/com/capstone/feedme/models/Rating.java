package com.capstone.feedme.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {


    // ATT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private long id;
    @Column(name = "rating")
    private int rating;

    @ManyToOne                              // Many ratings per one user
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne                              // Many ratings per one recipe
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private Recipe recipe;

    // CON
    public Rating() {
    }
    public Rating(int rating, User user, Recipe recipe) {
        this.rating = rating;
        this.user = user;
        this.recipe = recipe;
    }

    // GET
    public long getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public int getRating() {
        return rating;
    }

    // SET
    public void setUser(User user) {
        this.user = user;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }


    // CHECK
    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", rating=" + rating +
                ", user=" + user +
                ", recipe=" + recipe +
                '}';
    }


}  //<--END
