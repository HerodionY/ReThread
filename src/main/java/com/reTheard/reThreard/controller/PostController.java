package com.reTheard.reThreard.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.reTheard.reThreard.service.PostService;
import com.reTheard.reThreard.dto.PostRequest;
import com.reTheard.reThreard.model.Post;
import com.reTheard.reThreard.model.User;
import com.reTheard.reThreard.service.UserService;
import com.reTheard.reThreard.dto.PostResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.List;



@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/create-post")
public ResponseEntity<Map<String, Object>> createPost(@RequestBody PostRequest postRequest) {
    // Map postRequest to a Post entity
    Post post = new Post();
    post.setCaption(postRequest.getCaption());
    post.setMediaUrl(postRequest.getMediaUrl());
    post.setCreatedAt(LocalDateTime.now());
    // Assuming postRequest.getMediaType() returns a String
    String mediaTypeString = postRequest.getMediaType();

    // Convert the String to the enum (this will throw IllegalArgumentException if the string is invalid)
    Post.MediaType mediaType = Post.MediaType.valueOf(mediaTypeString.toUpperCase());

    // Set the converted enum value to the Post object
    post.setMediaType(mediaType);
    
    User user = userService.getUserById(postRequest.getUserId());
    if (user == null) {
        return ResponseEntity.status(404).body(Map.of("code", "404", "message", "User not found"));
    }
    
    post.setUser(user);
    Post createdPost = postService.createPost(post);
    
    if (createdPost == null) {
        return ResponseEntity.status(500).body(Map.of("code", "500", "message", "Failed to create post"));
    }
    
    // Map Post to PostResponse and return
    PostResponse postResponse = new PostResponse(createdPost);
    return ResponseEntity.ok(Map.of("code", "200", "message", "Post created successfully", "data", postResponse));
}



    
    @GetMapping("/post/{id}")
    public ResponseEntity<Map<String, Object>> getPostById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        Post post = postService.getPostById(id);
        if (post != null) {
            data.put("post", post);
            response.put("code", "200");
            response.put("message", "Post retrieved successfully");
            response.put("data", data);
            return ResponseEntity.ok(response);
        } else {
            response.put("code", "404");
            response.put("message", "Post not found");
            response.put("data", data);
            return ResponseEntity.status(404).body(response);
        }
    }
    
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<Map<String, Object>> getPostsByUserId(@PathVariable UUID userId) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        Set<Post> posts = postService.getAllPostsByUserId(userId);
        if (posts != null && !posts.isEmpty()) {
            data.put("posts", posts);
            response.put("code", "200");
            response.put("message", "Posts retrieved successfully");
            response.put("data", data);
            return ResponseEntity.ok(response);
        } else {
            response.put("code", "404");
            response.put("message", "No posts found for this user");
            response.put("data", data);
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

        List<Post> posts = postService.getAllPosts(page, size, sortBy);

        data.put("posts", posts);
        response.put("code", "200");
        response.put("message", "Posts retrieved successfully");
        response.put("data", data);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        postService.deletePost(id);
        if(id != null) {
            response.put("code", "200");            
            response.put("message", "Post deleted successfully");
            return ResponseEntity.ok().build();
        }else{
            response.put("code", "404");            
            response.put("message", "Post not found");
            return ResponseEntity.status(404).build();
        }
        
    }
}



    
    

