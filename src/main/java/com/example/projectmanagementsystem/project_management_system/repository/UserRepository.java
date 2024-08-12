package com.example.projectmanagementsystem.project_management_system.repository;

import com.example.projectmanagementsystem.project_management_system.model.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {
    List<User> getUsers();
    //User createUser(User user);
    Map<String, String> registerUser(User user);
}
