package com.reTheard.reThreard.controller;

import com.reTheard.reThreard.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = "*")
public class LikeController {

    @Autowired
    private LikeService likeService;

    // Endpoint to add a like to a post
    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<Map<String, String>> addLike(@PathVariable UUID userId, @PathVariable UUID postId) {
        Map<String, String> response = new HashMap<>();
    
        try {
            boolean success = likeService.addLikes(userId, postId);
            if (success) {
                response.put("message", "Like added successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                response.put("message", "You have already liked this post");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            // Log the exception details for better debugging
            response.put("message", "An error occurred while processing your request: " + e.getMessage());
            e.printStackTrace(); // Logs the stack trace to the console or log file
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    


    // Endpoint to remove a like from a post
    @DeleteMapping("/{userId}/{postId}")
    public ResponseEntity<String> removeLike(@PathVariable UUID userId, @PathVariable UUID postId) {
        String result = likeService.removeLike(userId, postId);
        if ("Like removed successfully".equals(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(400).body(result);  // Return 400 if not successful
    }
}
