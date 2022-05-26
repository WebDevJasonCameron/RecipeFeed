package com.capstone.feedme.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 50)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, length = 500)
    private String password;

    //account connects to the profile (one to one: one account for one profile)
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "profile")
    private Profile profile;

    //connects the account to the list of their posted comments (one to many: one profile for multiple comments)
    @OneToMany
    private List<Comment> postedComments;

    public Account() {}

    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // copy constructor for spring security
    public Account(Account copy){
        id = copy.id;
        username = copy.username;
        email = copy.email;
        password = copy.password;
        profile = copy.profile;
        postedComments = copy.postedComments;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Profile getProfile() {
        return profile;
    }

    public List<Comment> getPostedComments() {
        return postedComments;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setPostedComments(List<Comment> postedComments) {
        this.postedComments = postedComments;
    }
}
