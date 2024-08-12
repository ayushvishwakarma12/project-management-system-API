package com.example.projectmanagementsystem.project_management_system.repository;

import com.example.projectmanagementsystem.project_management_system.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<Project, Integer> {
}
