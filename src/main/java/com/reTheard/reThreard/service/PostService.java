package com.reTheard.reThreard.service;

import com.reTheard.reThreard.model.Post;
import com.reTheard.reThreard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    // Create a new post
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // Get a post by ID
    public Post getPostById(UUID id) {
        return postRepository.findById(id).orElse(null);
    }

    // Get all posts by user ID
    public Set<Post> getAllPostsByUserId(UUID user) {
        return postRepository.findByUserId(user);
    }

    // Get paginated posts with sorting
    public List<Post> getAllPosts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.getContent();
    }

    // Update an existing post
    public Post updatePost(Post post) {
        if (post.getId() == null) {
            throw new IllegalArgumentException("Post ID cannot be null");
        }

        if (postRepository.existsById(post.getId())) {
            return postRepository.save(post); // Save the updated post
        } else {
            throw new RuntimeException("Post not found with ID: " + post.getId());
        }
    }
}
