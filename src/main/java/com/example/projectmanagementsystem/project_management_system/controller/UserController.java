package com.example.projectmanagementsystem.project_management_system.controller;


import com.example.projectmanagementsystem.project_management_system.model.User;
import com.example.projectmanagementsystem.project_management_system.service.UserJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
