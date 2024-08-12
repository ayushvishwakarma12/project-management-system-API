package com.example.projectmanagementsystem.project_management_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@JsonIgnoreProperties("projects")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
   private UUID id;

    @Column(name = "name")
   private String name;

    @Column(name = "username")
   private String username;

    @Column(name = "password")
   private String password;

    @Column(name = "role")
   private String role;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @ManyToMany(mappedBy = "teamMembers")
    private List<Project> projects;

    public User(UUID id, String name, String username, String password, String role, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.projects = projects;
    }

    public User() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public enum Gender {
        MALE,
        FEMALE,
        NON_BINARY,
        OTHER
    }
}
