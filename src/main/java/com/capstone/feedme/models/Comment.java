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
    private Account account;

    public Comment() {}

    public Comment(String comment, String post_date, Recipe recipe, Account account) {
        this.comment = comment;
        this.post_date = post_date;
        this.recipe = recipe;
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public String getPost_date() {
        return post_date;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Account getAccount() {
        return account;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
