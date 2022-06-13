package com.capstone.feedme.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;
import javax.validation.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
public class User {

    // ATT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private long id;
    @Column(name = "username" ,nullable = false, length = 50)
    @NotBlank(message = "Please enter your username")
    private String username;
    @Column(name = "email" ,nullable = false, length = 100)
    @NotBlank(message = "Please enter your email")
    private String email;
    @Column(name = "password" ,nullable = false, length = 500)
    @NotBlank(message = "Please enter a password")
    private String password;
    @Column(name = "avatar" ,length = 500)
    private String avatar;
    @Column(name = "bio")
    @Type(type = "text")
    private String bio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",  orphanRemoval = true)     // recipes
    @JsonManagedReference
    private List<Recipe> userRecipes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",  orphanRemoval = true)     // comments
    @JsonManagedReference
    private List<Comment> userComments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rating",  orphanRemoval = true)     // ratings
    @JsonManagedReference
    private List<Rating> userRatings;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})                     // OWNER: favorites
    @JsonManagedReference
    @JoinTable(
            name = "user_favorites",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "recipe_id")}
    )
    private List<Recipe> userFavorites;


    // CON
    public User() {
    }
    public User(long id, String username) {
        this.id = id;
        this.username = username;
    }

    // COPY CON
    public User(User copy){
        id = copy.id;
        username = copy.username;
        email = copy.email;
        password = copy.password;
    }

    // GET
    public long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getAvatar() {
        return avatar;
    }
    public String getBio() {
        return bio;
    }
    public List<Recipe> getUserRecipes() {
        return userRecipes;
    }
    public List<Comment> getUserComments() {
        return userComments;
    }
    public List<Rating> getUserRatings() {
        return userRatings;
    }
    public List<Recipe> getUserFavorites() {
        return userFavorites;
    }



    // SET
    public void setId(long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public void setUserRecipes(List<Recipe> userRecipes) {
        this.userRecipes = userRecipes;
    }
    public void setUserComments(List<Comment> userComments) {
        this.userComments = userComments;
    }
    public void setUserRatings(List<Rating> userRatings) {
        this.userRatings = userRatings;
    }
    public void setUserFavorites(List<Recipe> userFavorites) {
        this.userFavorites = userFavorites;
    }


    // CHECK
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", bio='" + bio + '\'' +
                ", userRecipes=" + userRecipes +
                ", userComments=" + userComments +
                ", userRatings=" + userRatings +
                ", userFavorites=" + userFavorites +
                '}';
    }


}  //<--END
