package com.reTheard.reThreard.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "friendships")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    @JsonBackReference
    private User friend;

    @Column(nullable = false)
    private boolean isFollowed = true; // Default status is "Follow"

    // Default constructor
    public Friendship() {}

    // Custom constructor to make creation easier
    public Friendship(User user, User friend, boolean isFollowed) {
        this.user = user;
        this.friend = friend;
        this.isFollowed = isFollowed;
    }

    // Setter for 'isFollowed'
    public void setIsFollowed(boolean isFollowed) {
        this.isFollowed = isFollowed;
    }

    // Setter for 'friend'
    public void setFriend(User friend) {
        this.friend = friend;
    }
}
