package com.capstone.feedme.models;
import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "api_id")
    private int apiId;
    private String title;
    @Column(name = "img_url")
    private String imgUrl;
    private String summary;
    private String instruction;
    @Column(name = "ready_in_min")
    private int readyInMin;
    @Column(name = "serving_amount")
    private int servingAmount;
    @Column(name = "source_name")
    private String sourceName;
    @Column(name = "source_url")
    private String sourceUrl;
    private boolean vegetarian;
    private boolean vegan;
    @Column(name = "gluten_free")
    private boolean glutenFree;
    @Column(name = "dairy_free")

    // ADDITIONAL ATT
    private boolean dairyFree;
    @Column(length = 500)
    private String video_url;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany(mappedBy = "recipe")
    private List<User> userFavorites;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_categories",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> recipeCategories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<Ingredient> recipeIngredients;
}
