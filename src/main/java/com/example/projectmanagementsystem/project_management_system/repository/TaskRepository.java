package com.example.projectmanagementsystem.project_management_system.repository;

import com.example.projectmanagementsystem.project_management_system.model.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface TaskRepository {
    List<Task> getTasks();
    ResponseEntity<Map<String, String>> addTask(Task task);
}
