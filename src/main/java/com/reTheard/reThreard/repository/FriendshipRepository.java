package com.reTheard.reThreard.repository;



import com.reTheard.reThreard.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {
    // Tambahkan metode untuk query yang spesifik jika diperlukan
}

