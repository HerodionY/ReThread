package com.reTheard.reThreard.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.reTheard.reThreard.service.PostService;
import com.reTheard.reThreard.model.Post;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService PostService;

   
    @PostMapping("/create-post")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody Post entity) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
    
        // Pastikan PostService di-@Autowired
        Post createdPost = PostService.createPost(entity);
    
        if (createdPost != null) {
            data.put("post", createdPost);
            response.put("code", "200");
            response.put("message", "Post created successfully");
            response.put("data", data);
    
            return ResponseEntity.ok(response);
        } else {
            response.put("code", "500");
            response.put("message", "Failed to create post");
            return ResponseEntity.status(500).body(response);
        }
    }
    

    @GetMapping("/{userId}/{id}")
    public ResponseEntity<Map<String, Object>> getPostById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        Post post = PostService.getPostById(id);
        if(post != null) {
            data.put("post", post);
            response.put("code", "200");
            response.put("message", "Post retrieved successfully");
            response.put("data", data);

            return ResponseEntity.ok(response);
        } else {
            response.put("code", "404");
            response.put("message", "Post not found");
            return ResponseEntity.status(404).body(response);
        }
       
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPostsByUserId(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        Set<Post> posts = PostService.getAllPostsByUserId(id);

        if (posts != null && !posts.isEmpty()) {
            data.put("posts", posts);
            response.put("code", "200");
            response.put("message", "Posts retrieved successfully");
            response.put("data", data);
            return ResponseEntity.ok(response);
        } else {
            response.put("code", "404");
            response.put("message", "No posts found for this user");
            return ResponseEntity.status(404).body(response);
        }
    }
    @GetMapping("/home-page")
    public ResponseEntity<Map<String, Object>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        Iterable<Post> posts = PostService.getAllPosts(page, size, sortBy);

        data.put("posts", posts);
        response.put("code", "200");
        response.put("message", "Posts retrieved successfully");
        response.put("data", data);

        return ResponseEntity.ok(response);
    }


    
    
}
