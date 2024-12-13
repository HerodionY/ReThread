package com.reTheard.reThreard.model;


import jakarta.persistence.*;
import java.util.UUID;

import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    
    private String caption;
    private String mediaUrl;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Like> likes;

    public enum MediaType {
        IMAGE,
        VIDEO,
        TEXT
    }

    public Post(UUID id) {
        this.id = id;
    }

    

    // Getter dan Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }


    public Post() {}
}
