package com.capstone.feedme.models;
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
    private LocalDateTime timeStamp;

    @ManyToOne                              // Many comments per one user
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne                              // Many comments per one recipe
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;



    // CON
    public Comment() {
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
