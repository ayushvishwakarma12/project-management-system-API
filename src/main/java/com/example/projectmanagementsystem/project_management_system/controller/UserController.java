package com.example.projectmanagementsystem.project_management_system.controller;


import com.example.projectmanagementsystem.project_management_system.model.Task;
import com.example.projectmanagementsystem.project_management_system.model.User;
import com.example.projectmanagementsystem.project_management_system.service.UserJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserJpaService userJpaService;

    @GetMapping("/api/users/current-user")
    public String getLoggedInUser(Principal principal) {
        return principal.getName();

    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userJpaService.getUsers();
    }

    @PostMapping("/api/signup")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        try {
            Map<String, String> result = userJpaService.registerUser(user);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
        return userJpaService.getUserById(userId);
    }

    @GetMapping("/api/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return userJpaService.getUserByUsername(username).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/api/users/{userId}")
    public  ResponseEntity<User> updateUserById(@PathVariable UUID userId, @RequestBody User user) {
        return userJpaService.updateUserById(userId, user);
    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable UUID userId) {
        return userJpaService.deleteUserById(userId);
    }

}
