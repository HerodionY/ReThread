package com.reTheard.reThreard.model;



import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    

    public Like() {}

    // Constructor untuk memudahkan pembuatan like
    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
    }

    // Getter dan Setter
}
