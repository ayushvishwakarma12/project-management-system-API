package com.example.projectmanagementsystem.project_management_system.controller;


import com.example.projectmanagementsystem.project_management_system.model.Project;
import com.example.projectmanagementsystem.project_management_system.model.Task;
import com.example.projectmanagementsystem.project_management_system.service.TaskJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    TaskJpaService taskJpaService;


    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskJpaService.getTasks();
    }

    @PostMapping("/tasks")
    public ResponseEntity<Map<String, String>> addTask(@RequestBody Task task) {
        return taskJpaService.addTask(task);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable UUID taskId) {
        return taskJpaService.getTaskById(taskId);
    }

    @PutMapping("/tasks/{taskId}")
    public  ResponseEntity<Task> updateTaskById(@PathVariable UUID taskId, @RequestBody Task task) {
        return taskJpaService.updateTaskById(taskId, task);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<String> deleteTaskById(@PathVariable UUID taskId) {
        return taskJpaService.deleteTaskById(taskId);
    }

}
