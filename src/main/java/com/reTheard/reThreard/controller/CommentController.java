package com.reTheard.reThreard.controller;

import com.reTheard.reThreard.model.Comment;
import com.reTheard.reThreard.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Map<String, Object> response = new HashMap<>();

        Comment savedComment = commentService.addComment(comment);
        if(savedComment != null) {
            response.put("code", "200");
            response.put("message", "Comment added successfully");
            return ResponseEntity.ok(savedComment);
        }
        else {
            response.put("code", "400");
            response.put("message", "Invalid comment data");
            return ResponseEntity.badRequest().body(comment);
        }

        
        
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable UUID postId) {
        Map<String, Object> response = new HashMap<>();
        List<Comment> comments = commentService.getCommentsByPostId(postId);

        if (comments != null && !comments.isEmpty()) {
            response.put("code", "200");
            response.put("message", "Comments retrieved successfully");
            return ResponseEntity.ok(comments);
        } else {
            response.put("code", "404");            
            response.put("message", "No comments found for this post");
            return ResponseEntity.status(404).body(comments);
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
