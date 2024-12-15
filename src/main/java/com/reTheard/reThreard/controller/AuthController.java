package com.reTheard.reThreard.controller;

import com.reTheard.reThreard.model.LoginRequest; // Impor kelas LoginRequest
import com.reTheard.reThreard.model.User;
import com.reTheard.reThreard.service.AuthService;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService; // Suntikkan AuthService

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean success = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword()); // Gunakan objek authService
        User user = authService.authenticateAndGetUser(loginRequest.getEmail(), loginRequest.getPassword());
        
        if (user != null) { // Jika autentikasi berhasil
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> data = new HashMap<>();
    
            data.put("userId", user.getId());
            data.put("email", user.getEmail());
            response.put("code", "200");
            response.put("message", "Login successful!");
            response.put("data", data);
    
            return ResponseEntity.ok(response);
        } else { // Jika autentikasi gagal
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("code", "401", "message", "Invalid email or password"));
        }
    }
}