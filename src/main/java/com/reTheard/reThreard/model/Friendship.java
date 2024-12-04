package com.reTheard.reThreard.model;

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
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private User friend;

    @Column(nullable = false)
    private boolean isFollowed = true; // Default status is "Follow"

    public Friendship() {}

    public void setIsFollowed(boolean isFollowed) {
        this.isFollowed = isFollowed;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    
}
