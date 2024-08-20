package com.example.projectmanagementsystem.project_management_system.repository;

import com.example.projectmanagementsystem.project_management_system.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TaskRepository {
    List<Task> getTasks();
    ResponseEntity<Map<String, String>> addTask(Task task);
    ResponseEntity<Task> getTaskById(UUID taskId);
    ResponseEntity<Task> updateTaskById(UUID taskId, Task task);
    ResponseEntity<String> deleteTaskById(UUID taskId);
}
