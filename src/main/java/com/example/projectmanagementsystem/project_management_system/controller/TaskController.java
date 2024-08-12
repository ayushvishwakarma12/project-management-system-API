package com.example.projectmanagementsystem.project_management_system.controller;


import com.example.projectmanagementsystem.project_management_system.model.Task;
import com.example.projectmanagementsystem.project_management_system.service.TaskJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
}
