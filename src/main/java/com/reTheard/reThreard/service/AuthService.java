package com.reTheard.reThreard.service;



import com.reTheard.reThreard.model.LoginRequest;
import com.reTheard.reThreard.model.User; // Pastikan Anda memiliki model User
import com.reTheard.reThreard.repository.UserRepository; // Pastikan Anda memiliki repository
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            return user.getPassword().equals(password);
        }
        return false; 
    }

    public User authenticateAndGetUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Validasi password
            if (user.getPassword().equals(password)) { // Gunakan hashing jika password terenkripsi
                return user; // Kembalikan objek User jika autentikasi berhasil
            }
        }
        // Kembalikan null jika autentikasi gagal
        return null;
    }

   
    public User registerUser(String username, String email, String password) {
        
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // Simpan password dalam bentuk teks biasa

        return userRepository.save(user); // Simpan pengguna di database
    }
}