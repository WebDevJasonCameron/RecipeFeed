package com.capstone.feedme.models;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username" ,nullable = false, length = 50)
    private String username;
    @Column(name = "email" ,nullable = false, length = 100)
    private String email;
    @Column(name = "password" ,nullable = false, length = 500)
    private String password;
    @Column(name = "avatar" ,length = 500)
    private String avatar;
    @Column(name = "bio")
    @Type(type = "text")
    private String bio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Recipe> userRecipes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_favorite_recipes",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "recipe_id")}
    )
    private List<Recipe> favoriteRecipes;


}
