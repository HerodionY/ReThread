package com.reTheard.reThreard.service;

import com.reTheard.reThreard.model.Like;
import com.reTheard.reThreard.model.Post;
import com.reTheard.reThreard.model.User;
import com.reTheard.reThreard.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    // Menambahkan like pada post
    public boolean addLike(UUID userId, UUID postId) {
        // Cek apakah user sudah menyukai post ini
        Optional<Like> existingLike = likeRepository.findByUserIdAndPostId(userId, postId);
        if (existingLike.isPresent()) {
            return false; // Sudah menyukai, tidak perlu menambah lagi
        }

        // Buat objek Like baru dan simpan ke database
        Like newLike = new Like();
        newLike.setUser(new User(userId));  // Pastikan User sudah ada di database
        newLike.setPost(new Post(postId));  // Pastikan Post sudah ada di database
        likeRepository.save(newLike);
        return true;
    }

    // Menghapus like pada post
    public boolean removeLike(UUID userId, UUID postId) {
        // Cari like yang sudah ada berdasarkan user dan post
        Optional<Like> existingLike = likeRepository.findByUserIdAndPostId(userId, postId);
        if (existingLike.isEmpty()) {
            return false; // Tidak ada like yang ditemukan
        }

        // Hapus like dari database
        likeRepository.delete(existingLike.get());
        return true;
    }
}
