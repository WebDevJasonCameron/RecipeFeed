package com.capstone.feedme.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String foodAllergy;

    @ManyToMany(mappedBy = "category")
    private List<Recipe> recipes;

    public Category() {}

    public Category(String type, String foodAllergy, List<Recipe> recipes) {
        this.type = type;
        this.foodAllergy = foodAllergy;
        this.recipes = recipes;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getFoodAllergy() {
        return foodAllergy;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFoodAllergy(String foodAllergy) {
        this.foodAllergy = foodAllergy;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
