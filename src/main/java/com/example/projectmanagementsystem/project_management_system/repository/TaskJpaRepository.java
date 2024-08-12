package com.example.projectmanagementsystem.project_management_system.repository;

import com.example.projectmanagementsystem.project_management_system.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskJpaRepository extends JpaRepository<Task, UUID> {
}
