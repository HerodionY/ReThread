package com.reTheard.reThreard.service;

import com.reTheard.reThreard.model.Like;
import com.reTheard.reThreard.model.Post;
import com.reTheard.reThreard.model.User;
import com.reTheard.reThreard.repository.LikeRepository;
import com.reTheard.reThreard.repository.PostRepository;
import com.reTheard.reThreard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;  // For fetching Post from DB

    @Autowired
    private UserRepository userRepository;  // For fetching User from DB

    // Menambahkan like pada post
    public String addLike(UUID userId, UUID postId) {
        // Cek apakah user sudah menyukai post ini
        Optional<Like> existingLike = likeRepository.findByUserIdAndPostId(userId, postId);
        if (existingLike.isPresent()) {
            return "User already liked this post"; // Already liked
        }

        // Validate if Post and User exist
        Optional<Post> post = postRepository.findById(postId);
        Optional<User> user = userRepository.findById(userId);

        if (post.isEmpty()) {
            return "Post not found"; // Post doesn't exist
        }

        if (user.isEmpty()) {
            return "User not found"; // User doesn't exist
        }

        // Create a new Like object and save to the database
        Like newLike = new Like(post.get(), user.get());
        likeRepository.save(newLike);

        return "Like added successfully"; // Successfully liked
    }

    public boolean addLikes(UUID userId, UUID postId) {
        Optional<Like> existingLike = likeRepository.findByUserIdAndPostId(userId, postId);
        if (existingLike.isPresent()) {
            return false; // Like already exists
        }
    
        // Check if the User and Post exist in the database
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);
    
        if (!user.isPresent() || !post.isPresent()) {
            // Return false if either User or Post doesn't exist
            return false;
        }
    
        Like newLike = new Like();
        newLike.setUser(user.get());  // Ensure that the User exists in the database
        newLike.setPost(post.get());  // Ensure that the Post exists in the database
        likeRepository.save(newLike);
        return true; // Successfully added like
    }
    
    

    // Menghapus like pada post
    public String removeLike(UUID userId, UUID postId) {
        // Cari like yang sudah ada berdasarkan user dan post
        Optional<Like> existingLike = likeRepository.findByUserIdAndPostId(userId, postId);
        if (existingLike.isEmpty()) {
            return "Like not found"; // No like to remove
        }

        // Hapus like dari database
        likeRepository.delete(existingLike.get());
        return "Like removed successfully"; // Successfully removed
    }
}
