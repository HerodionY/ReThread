package com.reTheard.reThreard.repository;


import com.reTheard.reThreard.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    // Tambahkan metode untuk query yang spesifik jika diperlukan
}

