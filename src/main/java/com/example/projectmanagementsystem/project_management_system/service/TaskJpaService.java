package com.example.projectmanagementsystem.project_management_system.service;

import com.example.projectmanagementsystem.project_management_system.config.AuthController;
import com.example.projectmanagementsystem.project_management_system.model.Project;
import com.example.projectmanagementsystem.project_management_system.model.Task;
import com.example.projectmanagementsystem.project_management_system.repository.TaskJpaRepository;
import com.example.projectmanagementsystem.project_management_system.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TaskJpaService implements TaskRepository {
    @Autowired
    TaskJpaRepository taskJpaRepository;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Override
    public List<Task> getTasks() {
        return taskJpaRepository.findAll();
    }


    @Override
    public ResponseEntity<Map<String, String>> addTask(Task task) {
       Task newTask = new Task();
       newTask.setTitle(task.getTitle());
       newTask.setDescription(task.getDescription());
       newTask.setAssignTo(task.getAssignTo());
       newTask.setStatus(task.getStatus());
       newTask.setPriority(task.getPriority());

       taskJpaRepository.save(newTask);

       Map<String, String> response = new HashMap<>();
       logger.info(task.getTitle());
       response.put("response", "Task added successfully");

       return ResponseEntity.ok(response);

    }

    @Override
    public ResponseEntity<Task> getTaskById(@PathVariable UUID taskId) {
        try {
            Task task = taskJpaRepository.findById(taskId).get();
            return ResponseEntity.ok(task);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Task Id");
        }
    }


    @Override
    public  ResponseEntity<Task> updateTaskById(@PathVariable UUID taskId, @RequestBody Task task) {
        try {
            Task existingTask = taskJpaRepository.findById(taskId).get();
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setPriority(task.getPriority());
            existingTask.setAssignTo(task.getAssignTo());
            existingTask.setStatus(task.getStatus());


            taskJpaRepository.save(existingTask);
            return  ResponseEntity.ok(existingTask);


        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Task Data");
        }
    }

    @Override
    public ResponseEntity<String> deleteTaskById(@PathVariable UUID taskId) {
        try {
            taskJpaRepository.deleteById(taskId);
            return ResponseEntity.ok("Task Deleted Successfully");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Task Id");
        }
    }
}
