package com.capstone.feedme.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ratings")
public class Rating {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //one to one??
    //recipe
}
