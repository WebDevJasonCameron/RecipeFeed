package com.capstone.feedme.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String avatar;
    private String bio;


    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany
    private List<Recipe> recipeList;
}
