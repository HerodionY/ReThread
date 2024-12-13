package com.reTheard.reThreard.dto;

import java.util.UUID;

public class CommentDTO {

    private UUID postId;
    private UUID userId;
    private String content;

    // Default constructor
    public CommentDTO() {}

    // Constructor with all fields
    public CommentDTO(UUID postId, UUID userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
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
}
