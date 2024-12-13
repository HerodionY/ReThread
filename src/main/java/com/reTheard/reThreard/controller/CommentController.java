package com.reTheard.reThreard.controller;

import com.reTheard.reThreard.model.Comment;
import com.reTheard.reThreard.model.Post;
import com.reTheard.reThreard.model.User;
import com.reTheard.reThreard.service.CommentService;
import com.reTheard.reThreard.service.PostService;
import com.reTheard.reThreard.service.UserService;
import com.reTheard.reThreard.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    
    @PostMapping
public ResponseEntity<Map<String, Object>> addComment(@RequestBody CommentDTO commentDTO) {
    Map<String, Object> response = new HashMap<>();
    Map<String, Object> data = new HashMap<>();

    try {
        Post post = postService.getPostById(commentDTO.getPostId());
        User user = userService.getUserById(commentDTO.getUserId());

        if (post == null) {
            response.put("code", "404");
            response.put("message", "Post not found");
            return ResponseEntity.status(404).body(response);
        }

        if (user == null) {
            response.put("code", "404");
            response.put("message", "User not found");
            return ResponseEntity.status(404).body(response);
        }

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(commentDTO.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentService.addComment(comment);

        if (savedComment != null) {
            data.put("comment", savedComment);
            response.put("code", "200");
            response.put("message", "Comment added successfully");
            response.put("data", data);
            return ResponseEntity.ok(response);
        } else {
            response.put("code", "500");
            response.put("message", "Failed to add comment");
            return ResponseEntity.status(500).body(response);
        }
    } catch (Exception e) {
        response.put("code", "500");
        response.put("message", "An unexpected error occurred");
        response.put("error", e.getMessage());
        return ResponseEntity.status(500).body(response);
    }
}





    



    @GetMapping("/{postId}")
public ResponseEntity<Map<String, Object>> getCommentsByPostId(@PathVariable UUID postId) {
    Map<String, Object> response = new HashMap<>();
    List<Comment> comments = commentService.getCommentsByPostId(postId);

    if (comments != null && !comments.isEmpty()) {
        response.put("code", "200");
        response.put("message", "Comments retrieved successfully");
        response.put("data", comments);
        return ResponseEntity.ok(response);
    } else {
        response.put("code", "404");
        response.put("message", "No comments found for this post");
        response.put("data", new ArrayList<>());  // Return empty list if no comments found
        return ResponseEntity.status(404).body(response);
    }
}


    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID commentId) {
        Map<String, Object> response = new HashMap<>();
        commentService.deleteComment(commentId);
        if(commentId != null) {
            response.put("code", "200");            
            response.put("message", "Comment deleted successfully");
            return ResponseEntity.ok().build();
        }else{
            response.put("code", "404");            
            response.put("message", "Comment not found");
            return ResponseEntity.status(404).build();
        }

    }
}
