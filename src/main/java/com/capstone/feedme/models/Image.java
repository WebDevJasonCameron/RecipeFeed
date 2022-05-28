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
}
