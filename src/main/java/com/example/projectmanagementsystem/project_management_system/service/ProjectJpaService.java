package com.example.projectmanagementsystem.project_management_system.service;

import com.example.projectmanagementsystem.project_management_system.config.AuthController;
import com.example.projectmanagementsystem.project_management_system.model.Project;
import com.example.projectmanagementsystem.project_management_system.model.User;
import com.example.projectmanagementsystem.project_management_system.repository.ProjectJpaRepository;
import com.example.projectmanagementsystem.project_management_system.repository.ProjectRepository;
import com.example.projectmanagementsystem.project_management_system.repository.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProjectJpaService implements ProjectRepository {
    @Autowired
    ProjectJpaRepository projectJpaRepository;

    @Autowired
    UserJpaRepository userJpaRepository;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Override
    public List<Project> getAllProjects() {
        return projectJpaRepository.findAll();
    }

    @Override
    public ResponseEntity<Map<String, String>> addProject(Project project) {
        User manager = userJpaRepository.findById(project.getManager().getId())
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        logger.info(project.getProjectName());
        logger.info("Manager {}", manager.getName());

        Project newProject = new Project();
        newProject.setProjectName(project.getProjectName());
        newProject.setDescription(project.getDescription());
        newProject.setStartDate(project.getStartDate());
        newProject.setEndDate(project.getEndDate());
        newProject.setStatus(project.getStatus());
        newProject.setTeamMembers(project.getTeamMembers());
        newProject.setManager(manager);
        //newProject.setTasks(project.getTasks());

        projectJpaRepository.save(newProject);
        Map<String, String> response = new HashMap<>();
        response.put("project", "Project successfully registered");

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Project> getProjectById(UUID projectId) {
        try {
            Project project = projectJpaRepository.findById(projectId).get();
            return ResponseEntity.ok(project);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Project Id");
        }



    }

    @Override
    public ResponseEntity<Project> updateProjectById(UUID projectId, Project project) {
        try {
            Project existingProject = projectJpaRepository.findById(projectId).get();
            existingProject.setProjectName(project.getProjectName());
            existingProject.setStatus(project.getStatus());
            existingProject.setManager(project.getManager());
            existingProject.setTeamMembers(project.getTeamMembers());
            existingProject.setDescription(project.getDescription());
            existingProject.setStartDate(project.getStartDate());
            existingProject.setEndDate(project.getEndDate());
            projectJpaRepository.save(existingProject);
            return ResponseEntity.ok(existingProject);



        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Project Data");
        }
    }

    @Override
    public ResponseEntity<String> deleteProjectById(UUID projectId) {
        try {
            projectJpaRepository.deleteById(projectId);
            return ResponseEntity.ok("Project Deleted Successfully");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Project Id");
        }
    }


}
