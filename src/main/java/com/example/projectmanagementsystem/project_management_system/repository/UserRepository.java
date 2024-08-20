package com.example.projectmanagementsystem.project_management_system.repository;

import com.example.projectmanagementsystem.project_management_system.model.Task;
import com.example.projectmanagementsystem.project_management_system.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserRepository {
    List<User> getUsers();
    //User createUser(User user);
    Map<String, String> registerUser(User user);
    ResponseEntity<User> getUserById(UUID userId);
    ResponseEntity<User> updateUserById(UUID userId, User user);
    ResponseEntity<String> deleteUserById(UUID userId);
}
