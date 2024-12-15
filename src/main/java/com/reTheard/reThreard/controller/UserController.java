package com.reTheard.reThreard.controller;



import com.reTheard.reThreard.dto.RegisterUserRequest;
import com.reTheard.reThreard.model.User;
import com.reTheard.reThreard.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.HashMapChangeSet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
public ResponseEntity<Map<String, Object>> registerUser(@RequestBody RegisterUserRequest request) {
    Map<String, Object> response = new HashMap<>();

    // Manual validation
    if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
        response.put("code", "400");
        response.put("message", "Username is required");
        return ResponseEntity.badRequest().body(response);
    }
    if (request.getUsername().length() < 3 || request.getUsername().length() > 20) {
        response.put("code", "400");
        response.put("message", "Username must be between 3 and 20 characters");
        return ResponseEntity.badRequest().body(response);
    }
    if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
        response.put("code", "400");
        response.put("message", "Email is required");
        return ResponseEntity.badRequest().body(response);
    }
    if (!request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) { // Simple regex for email validation
        response.put("code", "400");
        response.put("message", "Invalid email format");
        return ResponseEntity.badRequest().body(response);
    }
    if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
        response.put("code", "400");
        response.put("message", "Password is required");
        return ResponseEntity.badRequest().body(response);
    }
    if (request.getPassword().length() < 6) {
        response.put("code", "400");
        response.put("message", "Password must be at least 6 characters long");
        return ResponseEntity.badRequest().body(response);
    }

    // If validation passes, proceed with user creation
    User user = new User(request.getUsername(), request.getEmail(), request.getPassword());
    User createdUser = userService.createUser(user);

    // Prepare response
    response.put("code", "200");
    response.put("message", "User created successfully");
    response.put("data", createdUser);

    return ResponseEntity.ok(response);
}


    @GetMapping("/{id}")
public ResponseEntity<Map<String, Object>> getUserById(@PathVariable UUID id) {
    Map<String, Object> response = new HashMap<>();
    User user = userService.getUserById(id);

    if (user != null) {
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);

        response.put("code", "200");
        response.put("message", "User retrieved successfully");
        response.put("data", data);

        return ResponseEntity.ok(response);
    } else {
        response.put("code", "404");
        response.put("message", "User not found");
        return ResponseEntity.status(404).body(response);
    }
}

@GetMapping("/search")
public ResponseEntity<Map<String, Object>> searchUserByUsername(@RequestParam String username) {
    Map<String, Object> response = new HashMap<>();
    Optional<User> userOptional = userService.getUserByUser(username);

    if (userOptional.isPresent()) {
        User user = userOptional.get();
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);

        Map<String, Object> status = new HashMap<>();
        status.put("code", "200");
        status.put("message", "User found successfully");

        response.put("status", status);
        response.put("data", data);

        return ResponseEntity.ok(response);
    } else {
        Map<String, Object> status = new HashMap<>();
        status.put("code", "404");
        status.put("message", "User not found");

        response.put("status", status);
        return ResponseEntity.status(404).body(response);
    }
}




    // Tambahkan metode lain sesuai kebutuhan
}
