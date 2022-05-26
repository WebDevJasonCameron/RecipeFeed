package com.capstone.feedme.models;
import javax.persistence.*;
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

    public Recipe() {}

    public Recipe(int rating, String visibility, int prep_time, int cook_time, int cook_temp, String cook_method, String skill_level, List<Image> postImages, List<Comment> commentList, List<Category> categories, List<Profile> favoriteProfiles, Profile profile) {
        this.rating = rating;
        this.visibility = visibility;
        this.prep_time = prep_time;
        this.cook_time = cook_time;
        this.cook_temp = cook_temp;
        this.cook_method = cook_method;
        this.skill_level = skill_level;
        this.postImages = postImages;
        this.commentList = commentList;
        this.categories = categories;
        this.favoriteProfiles = favoriteProfiles;
        this.profile = profile;
    }

    public long getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public String getVisibility() {
        return visibility;
    }

    public int getPrep_time() {
        return prep_time;
    }

    public int getCook_time() {
        return cook_time;
    }

    public int getCook_temp() {
        return cook_temp;
    }

    public String getCook_method() {
        return cook_method;
    }

    public String getSkill_level() {
        return skill_level;
    }

    public List<Image> getPostImages() {
        return postImages;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Profile> getFavoriteProfiles() {
        return favoriteProfiles;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public void setPrep_time(int prep_time) {
        this.prep_time = prep_time;
    }

    public void setCook_time(int cook_time) {
        this.cook_time = cook_time;
    }

    public void setCook_temp(int cook_temp) {
        this.cook_temp = cook_temp;
    }

    public void setCook_method(String cook_method) {
        this.cook_method = cook_method;
    }

    public void setSkill_level(String skill_level) {
        this.skill_level = skill_level;
    }

    public void setPostImages(List<Image> postImages) {
        this.postImages = postImages;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setFavoriteProfiles(List<Profile> favoriteProfiles) {
        this.favoriteProfiles = favoriteProfiles;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
