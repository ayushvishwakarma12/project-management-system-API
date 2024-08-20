package com.example.projectmanagementsystem.project_management_system.controller;

import com.example.projectmanagementsystem.project_management_system.config.AuthController;
import com.example.projectmanagementsystem.project_management_system.model.Project;
import com.example.projectmanagementsystem.project_management_system.service.ProjectJpaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {
@Autowired
    ProjectJpaService projectJpaService;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

@GetMapping("/api/projects")
    public List<Project> getAllProjects() {
    return projectJpaService.getAllProjects();
}

@PostMapping("/api/projects")
    public ResponseEntity<Map<String, String>> addProject(@RequestBody Project project) {
    logger.info("Request body {}", project.getManager());

    return  projectJpaService.addProject(project);
}

@GetMapping("/api/projects/{projectId}")
        public ResponseEntity<Project> getProjectById(@PathVariable UUID projectId) {
      return projectJpaService.getProjectById(projectId);
}

@PutMapping("/api/projects/{projectId}")
    public  ResponseEntity<Project> updateProjectById(@PathVariable UUID projectId, @RequestBody Project project) {
    logger.info(project.getProjectName());
    return projectJpaService.updateProjectById(projectId, project);
}

@DeleteMapping("/api/projects/{projectId}")
    public ResponseEntity<String> deleteProjectById(@PathVariable UUID projectId) {
    return projectJpaService.deleteProjectById(projectId);
}
}
