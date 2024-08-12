package com.example.projectmanagementsystem.project_management_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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


    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnoreProperties("tasks")
    private Project project;

    public Task() {}

    public Task(UUID id, String title, String description, TaskStatus status, Project project) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.project = project;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public enum TaskStatus {
        TO_DO,
        IN_PROGRESS,
        DONE
    }

}
