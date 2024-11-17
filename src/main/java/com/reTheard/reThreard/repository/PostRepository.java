package com.reTheard.reThreard.repository;

import com.reTheard.reThreard.model.Post;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;
public interface PostRepository extends JpaRepository<Post, UUID> {
    Set<Post> findByUserId(UUID userId);
}
