package com.reTheard.reThreard.repository;

import com.reTheard.reThreard.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByPostId(UUID postId); // Untuk mendapatkan semua komentar pada sebuah post
}
