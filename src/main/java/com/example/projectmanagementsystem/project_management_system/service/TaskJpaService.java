package com.example.projectmanagementsystem.project_management_system.service;

import com.example.projectmanagementsystem.project_management_system.config.AuthController;
import com.example.projectmanagementsystem.project_management_system.model.Task;
import com.example.projectmanagementsystem.project_management_system.repository.TaskJpaRepository;
import com.example.projectmanagementsystem.project_management_system.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskJpaService implements TaskRepository {
    @Autowired
    TaskJpaRepository taskJpaRepository;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public List<Task> getTasks() {
        return taskJpaRepository.findAll();
    }

    public ResponseEntity<Map<String, String>> addTask(Task task) {
       Task newTask = new Task();
       newTask.setTitle(task.getTitle());
       newTask.setDescription(task.getDescription());
       newTask.setProject(task.getProject());
       newTask.setStatus(task.getStatus());

       taskJpaRepository.save(newTask);

       Map<String, String> response = new HashMap<>();
       logger.info(task.getTitle());
       response.put("response", "Task added successfully");

       return ResponseEntity.ok(response);

    }
}
