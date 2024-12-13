package com.reTheard.reThreard.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @JsonBackReference
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors, if needed

    // Default constructor
    public Comment() {}

    // Constructor with content, post, and user
    public Comment(Post post, User user, String content) {
        this.post = post;
        this.user = user;
        this.content = content;
    }

    public Comment(UUID id, Post post, User user, String content, LocalDateTime createdAt) {
        this.id = id;
        this.post = post;
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
}
