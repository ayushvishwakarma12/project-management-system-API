package com.example.projectmanagementsystem.project_management_system.repository;

import com.example.projectmanagementsystem.project_management_system.model.Project;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProjectRepository {
    List<Project> getAllProjects();
    ResponseEntity<Map<String, String>> addProject(Project project);

}
