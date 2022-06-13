package com.capstone.feedme.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {

    // ATT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "comment", length = 2000)
    private String comment;
    @Column(name = "time_stamp")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")      // ADDED, MAY NEED TO REMOVE
    private LocalDateTime timeStamp;
    @Column(name = "original_user_id")
    private long originalUserId;            // id of user a comment is replied too

    @ManyToOne                              // Many comments per one user
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne                              // Many comments per one recipe
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private Recipe recipe;



    // CON
    public Comment() {
    }
    public Comment(Recipe recipe) {
        this.recipe = recipe;
    }

    // GET
    public long getId() {
        return id;
    }
    public String getComment() {
        return comment;
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    public User getUser() {
        return user;
    }
    public Recipe getRecipe() {
        return recipe;
    }


    // SET
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }


    // CHECK
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", timeStamp=" + timeStamp +
                ", user=" + user +
                ", recipe=" + recipe +
                '}';
    }


}  //<--END
