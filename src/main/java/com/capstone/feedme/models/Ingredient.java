package com.capstone.feedme.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    // ATT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "ingredient_name")
    private String ingredientName;
    @Column(name = "ingredient_amount")
    private String ingredientAmount;

    @ManyToOne                              // Many ingredients per one recipe
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;


    // CON
    public Ingredient() {
    }


    // GET
    public int getId() {
        return id;
    }
    public String getIngredientName() {
        return ingredientName;
    }
    public String getIngredientAmount() {
        return ingredientAmount;
    }
    public Recipe getRecipe() {
        return recipe;
    }


    // SET
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
    public void setIngredientAmount(String ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }


    // CHECK
    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredientName='" + ingredientName + '\'' +
                ", ingredientAmount='" + ingredientAmount + '\'' +
                ", recipe=" + recipe +
                '}';
    }


}  //<--END
