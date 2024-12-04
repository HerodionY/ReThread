package com.reTheard.reThreard.model;



import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.UUID;

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
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Like() {}

    // Constructor untuk memudahkan pembuatan like
    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
    }

    // Getter dan Setter
}