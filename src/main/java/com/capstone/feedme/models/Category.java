package com.capstone.feedme.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    // ATT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "type")
    private String type;

    @ManyToMany(mappedBy = "recipeCategories")             // NON OWNER
    private List<Recipe> recipes;


    // CON
    public Category() {
    }


    // GET
    public long getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public List<Recipe> getRecipes() {
        return recipes;
    }

    // SET
    public void setType(String type) {
        this.type = type;
    }
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }


    // CHECK
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", recipes=" + recipes +
                '}';
    }


}  //<--END
