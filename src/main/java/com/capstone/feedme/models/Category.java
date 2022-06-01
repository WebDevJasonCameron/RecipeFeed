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

    @ManyToMany(mappedBy = "recipe_categories")
    private List<Recipe> categoryRecipes;


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
    public List<Recipe> getCategoryRecipes() {
        return categoryRecipes;
    }


    // SET
    public void setType(String type) {
        this.type = type;
    }
    public void setCategoryRecipes(List<Recipe> categoryRecipes) {
        this.categoryRecipes = categoryRecipes;
    }


    // CHECK
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", categoryRecipes=" + categoryRecipes +
                '}';
    }



}  //<--END
