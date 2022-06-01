package com.capstone.feedme.models;

import javax.persistence.*;

@Entity
@Table(name = "user_recipes")
public class UserRecipe {

    @Column(length = 500)
    private String video_url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private Recipe recipe;
}
