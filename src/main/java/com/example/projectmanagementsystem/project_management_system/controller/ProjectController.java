package com.example.projectmanagementsystem.project_management_system.controller;

import com.example.projectmanagementsystem.project_management_system.model.Project;
import com.example.projectmanagementsystem.project_management_system.service.ProjectJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProjectController {
@Autowired
    ProjectJpaService projectJpaService;

@GetMapping("/api/projects")
    public List<Project> getAllProjects() {
    return projectJpaService.getAllProjects();
}

@PostMapping("/api/projects")
    public ResponseEntity<Map<String, String>> addProject(@RequestBody Project project) {
    return  projectJpaService.addProject(project);
}
}
