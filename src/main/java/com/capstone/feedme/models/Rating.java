package com.capstone.feedme.models;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {


    // ATT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // recipe_id



    // CON
    public Rating() {
    }


    // GET




    // SET



    // CHECK





}  //<--END
