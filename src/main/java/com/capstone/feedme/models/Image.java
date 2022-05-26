package com.capstone.feedme.models;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 500)
    private String image;

    //connects the image to the recipe post (many to one: multiple images for one post)
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Image() {}

    public Image(String image, Recipe recipe) {
        this.image = image;
        this.recipe = recipe;
    }

    public long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public Recipe getProfile() {
        return recipe;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setProfile(Recipe recipe) {
        this.recipe = recipe;
    }
}
