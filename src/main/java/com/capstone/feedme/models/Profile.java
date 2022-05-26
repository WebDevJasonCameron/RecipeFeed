package com.capstone.feedme.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 500)
    private String avatar;
    private String bio;

    //connects the profile information to the account info (one to one: one account for one profile)
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    //connects the profile to the list of their recipes posted (one to many: one profile for multiple recipes)
    @OneToMany
    private List<Recipe> postedRecipes;

    //connects the profile to the list of their posted comments (one to many: one profile for multiple comments)
    @OneToMany
    private List<Comment> postedComments;

    //connects the profile to multiple favorite posts (many to many: multiple profiles can favorite multiple posts)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favorite_recipes",
            joinColumns = {@JoinColumn(name = "profile_id")},
            inverseJoinColumns = {@JoinColumn(name = "recipe_id")}
    )
    private List<Recipe> favoriteRecipes;
}
