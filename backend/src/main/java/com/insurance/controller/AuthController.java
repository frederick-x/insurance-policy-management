package com.insurance.controller;

import com.insurance.model.User;
import com.insurance.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Registration successful");
            response.put("userId", registeredUser.getId());
            response.put("username", registeredUser.getUsername());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        Optional<User> user = userService.loginUser(username, password);
        
        if (user.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("userId", user.get().getId());
            response.put("username", user.get().getUsername());
            response.put("email", user.get().getEmail());
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid username or password");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }
}
