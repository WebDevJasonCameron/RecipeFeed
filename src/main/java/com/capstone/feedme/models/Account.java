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
}
