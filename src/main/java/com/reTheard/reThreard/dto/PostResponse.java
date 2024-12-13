package com.reTheard.reThreard.dto;

import com.reTheard.reThreard.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostResponse {
    private UUID id;
    private String caption;
    private String mediaUrl;
    private String mediaType;
    private LocalDateTime createdAt;
    private UUID userId;
    private String userName;

    // Constructor untuk mempermudah inisialisasi
    public PostResponse(com.reTheard.reThreard.model.Post post) {
        this.id = post.getId();
        this.caption = post.getCaption();
        this.mediaUrl = post.getMediaUrl();
        this.mediaType = post.getMediaType().toString();
        this.createdAt = post.getCreatedAt();

        // Ambil informasi dari objek User
        User user = post.getUser();
        if (user != null) {
            this.userId = user.getId();
            this.userName = user.getUsername(); // Pastikan `User` memiliki properti `name`
        }
    }

    // Getters dan Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
