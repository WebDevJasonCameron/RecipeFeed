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

    //connects the profile to multiple favorite posts (many to many: multiple profiles can favorite multiple posts)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favorite_recipes",
            joinColumns = {@JoinColumn(name = "profile_id")},
            inverseJoinColumns = {@JoinColumn(name = "recipe_id")}
    )
    private List<Recipe> favoriteRecipes;

    public Profile() {}

    public Profile(String avatar, String bio, Account account, List<Recipe> postedRecipes, List<Recipe> favoriteRecipes) {
        this.avatar = avatar;
        this.bio = bio;
        this.account = account;
        this.postedRecipes = postedRecipes;
        this.favoriteRecipes = favoriteRecipes;
    }

    public long getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getBio() {
        return bio;
    }

    public Account getAccount() {
        return account;
    }

    public List<Recipe> getPostedRecipes() {
        return postedRecipes;
    }

    public List<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setPostedRecipes(List<Recipe> postedRecipes) {
        this.postedRecipes = postedRecipes;
    }

    public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }
}
