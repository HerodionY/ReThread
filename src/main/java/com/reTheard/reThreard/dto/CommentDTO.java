package com.reTheard.reThreard.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommentDTO {

    private UUID postId;
    private UUID id;
    private UUID userId;
    private String username;
    private String content;
    private LocalDateTime createdAt;

    // Default constructor
    public CommentDTO() {}

    // Constructor with all fields
    public CommentDTO(UUID postId, UUID userId, String content, LocalDateTime createdAt) {
        this.id = UUID.randomUUID();
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CommentDTO(UUID id, UUID userId, String username, String content, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
    }
}
