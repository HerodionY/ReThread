package com.reTheard.reThreard.controller;



import com.reTheard.reThreard.model.User;
import com.reTheard.reThreard.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.HashMapChangeSet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        User createdUser = userService.createUser(user);
        data.put("user", createdUser);
        response.put("code", "200");
        response.put("message", "User created successfully");
        response.put("data", data);

        
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


    // Tambahkan metode lain sesuai kebutuhan
}
