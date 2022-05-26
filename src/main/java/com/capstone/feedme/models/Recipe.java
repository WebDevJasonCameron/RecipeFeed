package com.capstone.feedme.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import javax.persistence.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //limit to 5
    private int rating;
    @Column(length = 10)
    private String visibility;
    private int prep_time;
    private int cook_time;
    private int cook_temp;
    @Column(length = 500)
    private String cook_method;
    private String skill_level;

    //connects the recipe to its images (one to many: many images to one recipe)
    @OneToMany
    private List<Image> postImages;

    //connects the recipe to its comments (one to many: many comments to one recipe)
    @OneToMany
    private List<Comment> commentList;

    //connects the recipe to the categories it is related to (many to many: multiple categories to multiple recipes)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_categories",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories;

    @ManyToMany(mappedBy = "recipe")
    private List<Profile> favoriteProfiles;

    //connects the recipe to the profile that posted (many to one: many recipes to one profile)
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
