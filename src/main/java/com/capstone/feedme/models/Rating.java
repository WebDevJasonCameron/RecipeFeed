package com.capstone.feedme.models;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {


    // ATT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private long id;

    @ManyToOne                              // Many ratings per one user
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne                              // Many ratings per one recipe
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    // CON
    public Rating() {
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


    // SET
    public void setUser(User user) {
        this.user = user;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }


    // CHECK
    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", user=" + user +
                ", recipe=" + recipe +
                '}';
    }

}  //<--END
