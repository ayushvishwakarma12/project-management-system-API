package com.example.projectmanagementsystem.project_management_system.service;


import com.example.projectmanagementsystem.project_management_system.config.AuthController;
import com.example.projectmanagementsystem.project_management_system.model.User;
import com.example.projectmanagementsystem.project_management_system.repository.UserJpaRepository;
import com.example.projectmanagementsystem.project_management_system.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserJpaService implements UserRepository {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final List<User> store = new ArrayList<>();

//
//    public UserService() {
//        store.add(new User(1, "Alex", "alex@123.gmail.com", "abc"));
//        store.add(new User(2, "Alice", "alice@123.gmail.com", "abc"));
//    }

//    public List<User> getUsers() {
//        return this.store;
//    }

    public List<User> getUsers() {
        return userJpaRepository.findAll();
    }

//    public User createUser(User user) {
//        return userJpaRepository.save(user);
//    }

    public Map<String, String> registerUser(User user) {
        if (userJpaRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setName(user.getName());
        newUser.setRole(user.getRole());
        newUser.setGender(user.getGender());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        logger.info("Encoded Password: {}", encodedPassword);
//        logger.info("Gender: {}", user.getGender());

        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userJpaRepository.save(newUser);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User successfully registered");
        return response;
    }

}
