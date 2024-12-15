package com.reTheard.reThreard.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PostRequest {
    private UUID userId; // Only the user ID, not the User object
    private String caption;
    private List<String> mediaUrl;
    private LocalDateTime createdAt;
    private String mediaType;
    private String imageData; // Base64 encoded image data
    private String imageName; // Name of the image file to be saved

    // Getters and Setters
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<String> getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(List<String> mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
