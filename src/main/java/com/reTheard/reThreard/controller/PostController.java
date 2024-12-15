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
import com.reTheard.reThreard.dto.UserDTO;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.List;
import java.util.Base64;
import java.io.File;



@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    
    
    @PostMapping("/create-post")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody PostRequest postRequest) {
        try {
            // Log incoming request
            System.out.println("Request received: " + postRequest);
    
            // Map request to Post
            Post post = new Post();
            post.setCaption(postRequest.getCaption());
            List<String> mediaUrls = new ArrayList<>();
    
            // Handle the Base64 image data
            if (postRequest.getImageData() != null && !postRequest.getImageData().isEmpty()) {
                String imageData = postRequest.getImageData();
                String imageName = postRequest.getImageName();
                String filePath = "path/to/save/images/" + imageName;
    
                // Extract Base64 data (assuming it has a prefix like "data:image/jpeg;base64,")
                String base64Image = imageData.split(",")[1];
                byte[] decodedImage = Base64.getDecoder().decode(base64Image);
    
                // Save the image to the server
                File imageFile = new File(filePath);
                Files.write(imageFile.toPath(), decodedImage);
    
                // Add file path to media URLs
                mediaUrls.add(filePath);
            }
    
            post.setMediaUrl(mediaUrls);
            post.setCreatedAt(LocalDateTime.now());
    
            // Validate media type
            String mediaTypeString = postRequest.getMediaType();
            try {
                Post.MediaType mediaType = Post.MediaType.valueOf(mediaTypeString.toUpperCase());
                post.setMediaType(mediaType);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid media type: " + mediaTypeString);
                return ResponseEntity.status(400).body(Map.of("code", "400", "message", "Invalid media type"));
            }
    
            // Validate user
            User user = userService.getUserById(postRequest.getUserId());
            if (user == null) {
                System.err.println("User not found for ID: " + postRequest.getUserId());
                return ResponseEntity.status(404).body(Map.of("code", "404", "message", "User not found"));
            }
            post.setUser(user);
            System.out.println("User validated: " + user);
    
            // Save post
            Post createdPost = postService.createPost(post);
            if (createdPost == null) {
                System.err.println("Failed to create post");
                return ResponseEntity.status(500).body(Map.of("code", "500", "message", "Failed to create post"));
            }
    
            // Success response
            System.out.println("Post created successfully: " + createdPost);
            PostResponse postResponse = new PostResponse(createdPost);
            return ResponseEntity.ok(Map.of("code", "200", "message", "Post created successfully", "data", postResponse));
    
        } catch (Exception e) {
            e.printStackTrace(); // Log full stack trace
            return ResponseEntity.status(500).body(Map.of("code", "500", "message", "Internal server error"));
        }
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

        List<Map<String, Object>> postResponses = posts.stream()
                .map(post -> {
                    User user = post.getUser(); // Ambil user dari post
                    UserDTO userDTO = new UserDTO(user.getId(), user.getUsername()); // Buat UserDTO
                    
                    List<Map<String, Object>> comments = post.getComments().stream().map(comment -> {
                        Map<String, Object> commentData = new HashMap<>();
                        commentData.put("commentId", comment.getId());
                        commentData.put("content", comment.getContent());
                        commentData.put("commentedBy", new UserDTO(comment.getUser().getId(), comment.getUser().getUsername()));
                        commentData.put("commentedAt", comment.getCreatedAt());
                        return commentData;
                    }).toList();

                    List<Map<String, Object>> likes = post.getLikes().stream().map(like -> {
                        Map<String, Object> likeData = new HashMap<>();
                        likeData.put("likeId", like.getId());
                        likeData.put("likedBy", new UserDTO(like.getUser().getId(), like.getUser().getUsername()));
                        return likeData;
                    }).toList();
                    Map<String, Object> postMap = new HashMap<>();
                    postMap.put("postId", post.getId());
                    postMap.put("caption", post.getCaption());
                    postMap.put("mediaUrl", post.getMediaUrl());
                    postMap.put("createdAt", post.getCreatedAt());
                    postMap.put("mediaType", post.getMediaType());
                    postMap.put("user", userDTO); 
                    postMap.put("comments", comments);
                    postMap.put("likes", likes);

                    return postMap;
                })
                .toList();
            
        
       
        response.put("code", "200");
        response.put("message", "Posts retrieved successfully");
        response.put("data", postResponses);

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



    
    

