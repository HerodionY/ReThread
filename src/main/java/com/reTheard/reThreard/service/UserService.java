package com.reTheard.reThreard.service;

import com.reTheard.reThreard.model.User;
import com.reTheard.reThreard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        // Hash password dan simpan user
        return userRepository.save(user);
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    // Tambahkan metode lain sesuai kebutuhan
}
