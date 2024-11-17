package com.reTheard.reThreard.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ping {

    @CrossOrigin("*")
    @PostMapping("/ping")
    public ResponseEntity<Map<String,Object>> ping() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "pong");
        return ResponseEntity.ok(response);
    }
    
}
