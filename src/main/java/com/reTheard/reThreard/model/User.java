package com.reTheard.reThreard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String profilePicture;

    @Column(length = 500)
    private String bio;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friendship> friendships;

    @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friendship> followers;

     // Default constructor (required by JPA)
     public User() {
    }

    // Constructor hanya dengan userId
    public User(UUID id) {
        this.id = id;
    }

    // Constructor dengan semua field (kecuali ID dan relasi)
    public User(String username, String email, String password, String profilePicture, String bio) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.bio = bio;
    }

    // Constructor minimal untuk kebutuhan registrasi
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Constructors, Getters, and Setters
}
