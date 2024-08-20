package com.example.projectmanagementsystem.project_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private TaskPriority priority;


//    @ManyToOne
//    @JoinColumn(name = "project_id", nullable = false)
//    @JsonIgnoreProperties("tasks")
//    private Project project;

    //@ManyToOne
    @Column(name = "user_id")
    private UUID assignTo;

    public Task() {}

    public Task(UUID id, String title, String description, TaskStatus status, UUID assignTo, TaskPriority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignTo = assignTo;
        this.priority = priority;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//    }

    public UUID getAssignTo() {
        return this.assignTo;
    }

    public void setAssignTo(UUID assignTo) {
        this.assignTo = assignTo;
    }

    public TaskPriority getPriority() {
        return this.priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public enum TaskStatus {
        TO_DO,
        IN_PROGRESS,
        COMPLETED
    }

    public enum TaskPriority {
        LOW,
        MEDIUM,
        HIGH
    }

}
