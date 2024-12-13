package com.reTheard.reThreard.controller;

import com.reTheard.reThreard.model.Friendship;
import com.reTheard.reThreard.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/friendships")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    // Endpoint to follow a user
    @PostMapping("/follow")
    public ResponseEntity<Map<String, Object>> followUser(@RequestParam UUID userId, @RequestParam UUID friendId) {
        try {
            Friendship savedFriendship = friendshipService.createFollow(userId, friendId);

            Map<String, Object> response = new HashMap<>();
            response.put("code", "200");
            response.put("message", "User followed successfully");
            response.put("data", savedFriendship);

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", "400");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Endpoint to get followed users by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getFollowedByUserId(@PathVariable UUID userId) {
        List<Friendship> followedUsers = friendshipService.getFollowedByUserId(userId);

        Map<String, Object> response = new HashMap<>();
        if (followedUsers != null && !followedUsers.isEmpty()) {
            response.put("code", "200");
            response.put("message", "Followed users retrieved successfully");
            response.put("data", followedUsers);
            return ResponseEntity.ok(response);
        } else {
            response.put("code", "404");
            response.put("message", "No followed users found");
            return ResponseEntity.status(404).body(response);
        }
    }

    // Endpoint to unfollow a user
    @DeleteMapping("/unfollow")
    public ResponseEntity<Map<String, Object>> unfollowUser(@RequestParam UUID userId, @RequestParam UUID friendId) {
        try {
            friendshipService.unfollow(userId, friendId);

            Map<String, Object> response = new HashMap<>();
            response.put("code", "200");
            response.put("message", "User unfollowed successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", "400");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
