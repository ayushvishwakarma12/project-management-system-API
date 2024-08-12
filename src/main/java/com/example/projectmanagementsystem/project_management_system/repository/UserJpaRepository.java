package com.example.projectmanagementsystem.project_management_system.repository;

import com.example.projectmanagementsystem.project_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
