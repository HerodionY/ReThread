package com.reTheard.reThreard.model;


import jakarta.persistence.*;
import java.util.UUID;

import org.springframework.cglib.core.Local;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    
    private String caption;
    private String mediaUrl;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Like> likes;

    public enum MediaType {
        IMAGE,
        VIDEO,
        TEXT
    }

    

    // Getter dan Setter
}
