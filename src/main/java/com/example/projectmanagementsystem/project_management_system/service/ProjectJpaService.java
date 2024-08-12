package com.example.projectmanagementsystem.project_management_system.service;

import com.example.projectmanagementsystem.project_management_system.config.AuthController;
import com.example.projectmanagementsystem.project_management_system.model.Project;
import com.example.projectmanagementsystem.project_management_system.model.User;
import com.example.projectmanagementsystem.project_management_system.repository.ProjectJpaRepository;
import com.example.projectmanagementsystem.project_management_system.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectJpaService implements ProjectRepository {
    @Autowired
    ProjectJpaRepository projectJpaRepository;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Override
    public List<Project> getAllProjects() {
        return projectJpaRepository.findAll();
    }

    @Override
    public ResponseEntity<Map<String, String>> addProject(Project project) {
        logger.info(project.getProjectName());

        Project newProject = new Project();
        newProject.setProjectName(project.getProjectName());
        newProject.setDescription(project.getDescription());
        newProject.setStartDate(project.getStartDate());
        newProject.setEndDate(project.getEndDate());
        newProject.setStatus(project.getStatus());
        newProject.setTeamMembers(project.getTeamMembers());
        newProject.setManager(project.getManager());
        newProject.setTasks(project.getTasks());

        projectJpaRepository.save(newProject);
        Map<String, String> response = new HashMap<>();
        response.put("project", "Project successfully registered");

        return ResponseEntity.ok(response);
    }


}
