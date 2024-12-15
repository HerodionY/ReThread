package com.reTheard.reThreard.dto;

import com.reTheard.reThreard.model.Media;
import com.reTheard.reThreard.model.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PostResponse {
    private UUID id;
    private String caption;
    private List<Media> mediaUrl; // Assuming mediaUrl holds URLs or Base64 data
    private String mediaType;
    private LocalDateTime createdAt;
    private UUID userId;
    private String userName;

    // Constructor for easy initialization
    public PostResponse(com.reTheard.reThreard.model.Post post) {
        this.id = post.getId();
        this.caption = post.getCaption();
        this.mediaUrl = post.getMedia();  // Assuming List<String> for media URL or Base64
        this.mediaType = post.getMediaType().toString();  // Enum to String conversion
        this.createdAt = post.getCreatedAt();

        // Extract user details
        User user = post.getUser();
        if (user != null) {
            this.userId = user.getId();
            this.userName = user.getUsername(); // Ensure 'getUsername()' is available in the User class
        }
    }

    // Getters and Setters
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

    public List<Media> getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(List<Media> mediaUrl) {
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
