package com.capstone.feedme.models;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 500)
    private String comment;
    @Column(length = 10)
    private String post_date;

    //connects the comment to the recipe post (many to one: many comments to one recipe)
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    //connects the comment to the profile that posted it (many to one: many comments to one profile)
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;


}
