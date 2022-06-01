package com.capstone.feedme.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 500)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //many to many to recipe?
}
