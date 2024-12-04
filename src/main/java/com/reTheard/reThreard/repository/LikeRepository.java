package com.reTheard.reThreard.repository;

import com.reTheard.reThreard.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
    // Tambahkan metode untuk query yang spesifik jika diperlukan
    Optional<Like> findByUserIdAndPostId(UUID userId, UUID postId);
}
