package com.reTheard.reThreard.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PostRequest {
    private UUID userId; // Only the user ID, not the User object
    private String caption;
    private List<MediaRequest> media; // List of media objects, instead of mediaUrl
    private LocalDateTime createdAt;
    private String mediaType;

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

    public List<MediaRequest> getMedia() {
        return media;
    }

    public void setMedia(List<MediaRequest> media) {
        this.media = media;
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
}
