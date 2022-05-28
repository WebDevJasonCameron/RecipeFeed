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

    //connects the categories to multiple posts (many to many: multiple categories can connect to multiple posts)
    @ManyToMany(mappedBy = "category")
    private List<Recipe> recipes;
}
