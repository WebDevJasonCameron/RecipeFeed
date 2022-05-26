package com.capstone.feedme.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int rating;
    private int prep_time;
    private int cook_time;
    private int cook_temp;
    private String cook_method;
    private String skill_level;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_categories",
            joinColumns = {@JoinColumn(name="recipe_id")},
            inverseJoinColumns = {@JoinColumn(name="category_id")}
    )
    private List<Category> categories;
}
