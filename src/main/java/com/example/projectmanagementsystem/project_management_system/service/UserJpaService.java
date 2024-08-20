package com.example.projectmanagementsystem.project_management_system.service;


import com.example.projectmanagementsystem.project_management_system.config.AuthController;
import com.example.projectmanagementsystem.project_management_system.model.Task;
import com.example.projectmanagementsystem.project_management_system.model.User;
import com.example.projectmanagementsystem.project_management_system.repository.UserJpaRepository;
import com.example.projectmanagementsystem.project_management_system.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserJpaService implements UserRepository {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        //String encodedPassword = passwordEncoder.encode(user.getPassword());
//        logger.info("Encoded Password: {}", encodedPassword);
//        logger.info("Gender: {}", user.getGender());

        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userJpaRepository.save(newUser);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User successfully registered");
        return response;
    }

    @Override
    public ResponseEntity<User> getUserById(UUID userId) {
        try {
            User user = userJpaRepository.findById(userId).get();
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User Id");
        }
    }


    @Override
    public  ResponseEntity<User> updateUserById(UUID userId, User user){
        try {
            User existingUser = userJpaRepository.findById(userId).get();
            existingUser.setName(user.getName());
            existingUser.setUsername(user.getUsername());
            existingUser.setRole(user.getRole());
            existingUser.setGender(user.getGender());
            existingUser.setProjects(user.getProjects());

            userJpaRepository.save(existingUser);
            return ResponseEntity.ok(existingUser);


        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Task Data");
        }
    }


    @Override
    public ResponseEntity<String> deleteUserById(UUID userId){
        try {
            userJpaRepository.deleteById(userId);
            return ResponseEntity.ok("User Deleted Successfully");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User Id");
        }
    }

    public Optional<User> getUserByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

}
