package com.capstone.feedme.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {

    // ATT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "api_id", unique = true)
    private Long apiId;
    @Column(name = "user_recipe_id")
    private long userId;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "img_url")
    private String imgUrl;
    @Column(name = "summary", columnDefinition="TEXT")
    private String summary;
    @Column(name = "instruction", columnDefinition="TEXT")
    private String instruction;
    @Column(name = "ready_in_min")
    private String readyInMin;
    @Column(name = "serving_amount")
    private String servingAmount;
    @Column(name = "source_name")
    private String sourceName;
    @Column(name = "source_url")
    private String sourceUrl;
    @Column(name = "vegetarian")
    private boolean vegetarian;
    @Column(name = "vegan")
    private boolean vegan;
    @Column(name = "gluten_free")
    private boolean glutenFree;
    @Column(name = "dairy_free")
    private boolean dairyFree;
    @Column(length = 500)
    private String video_url;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", orphanRemoval = true)
    @JsonManagedReference
    private List<Ingredient> ingredients;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", orphanRemoval = true)
    @JsonManagedReference
    private List<Rating> recipeRatings;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToMany(mappedBy = "userFavorites")      // NON OWNER
    @JsonBackReference
    private List<User> userFavorites;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // OWNER <--REMEMBER: API has this as "dishType"
    @JsonManagedReference
    @JoinTable(
            name = "recipe_categories",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> recipeCategories;



    // CON
    public Recipe() {
    }
    public Recipe(long apiId, String title, String imgUrl) {
        this.apiId = apiId;
        this.title = title;
        this.imgUrl = imgUrl;
    }

    // GET
    public long getId() {
        return id;
    }
    public long getApiId() {
        return apiId;
    }
    public long getUserId() {
        return userId;
    }
    public String getTitle() {
        return title;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public String getSummary() {
        return summary;
    }
    public String getInstruction() {
        return instruction;
    }
    public String getReadyInMin() {
        return readyInMin;
    }
    public String getServingAmount() {
        return servingAmount;
    }
    public String getSourceName() {
        return sourceName;
    }
    public String getSourceUrl() {
        return sourceUrl;
    }
    public boolean isVegetarian() {
        return vegetarian;
    }
    public boolean isVegan() {
        return vegan;
    }
    public boolean isGlutenFree() {
        return glutenFree;
    }
    public boolean isDairyFree() {
        return dairyFree;
    }
    public String getVideo_url() {
        return video_url;
    }
    public User getUser() {
        return user;
    }
    public List<User> getUserFavorites() {
        return userFavorites;
    }
    public List<Category> getRecipeCategories() {
        return recipeCategories;
    }
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    public List<Rating> getRecipeRatings() {
        return recipeRatings;
    }

    // SET
    public void setApiId(long apiId) {
        this.apiId = apiId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
    public void setReadyInMin(String readyInMin) {
        this.readyInMin = readyInMin;
    }
    public void setServingAmount(String servingAmount) {
        this.servingAmount = servingAmount;
    }
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }
    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }
    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }
    public void setDairyFree(boolean dairyFree) {
        this.dairyFree = dairyFree;
    }
    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setUserFavorites(List<User> userFavorites) {
        this.userFavorites = userFavorites;
    }
    public void setRecipeCategories(List<Category> recipeCategories) {
        this.recipeCategories = recipeCategories;
    }
    public void setingredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public void setRecipeRatings(List<Rating> recipeRatings) {
        this.recipeRatings = recipeRatings;
    }

    // CHECK
    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", apiId=" + apiId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", summary='" + summary + '\'' +
                ", instruction='" + instruction + '\'' +
                ", readyInMin='" + readyInMin + '\'' +
                ", servingAmount='" + servingAmount + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", vegetarian=" + vegetarian +
                ", vegan=" + vegan +
                ", glutenFree=" + glutenFree +
                ", dairyFree=" + dairyFree +
                ", video_url='" + video_url + '\'' +
                ", ingredients=" + ingredients +
                ", recipeRatings=" + recipeRatings +
                ", user=" + user +
                ", userFavorites=" + userFavorites +
                ", recipeCategories=" + recipeCategories +
                '}';
    }


} //<--END
