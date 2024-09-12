# Project Management System API

## Overview

The Project Management System API is a Spring Boot backend application designed to manage projects, tasks, and users. It provides endpoints for project creation, task management, and user authentication.

## Features

- **Project Management:** Create, read, update, and delete projects.
- **Task Management:** Create, read, update, and delete tasks.
- **User Management:** User authentication, registration, and management.

## Project Structure

The project is organized into the following folders:

- **config:** Contains configurations for authentication and security.
- **controller:** Contains REST controllers for handling HTTP requests related to projects, tasks, and users.
- **model:** Defines the data models for projects, tasks, and users.
- **repository:** Contains data repositories for interacting with the database.
- **security:** Contains security-related configurations.
- **service:** Contains business logic and service implementations.

## Endpoints

### ProjectController

- **Get All Projects**
  ```http
  GET /api/projects

- **Add a Project**
  ```http
  POST /api/projects
  ```

- **Get Project By ID**
  ```http
  POST /api/projects/{projectId}
  ```
- **Update Project By ID**
  ```http
  PUT /api/projects/{projectId}
  ```
- **Delete Project By ID**
  ```http
  DELETE /api/projects/{projectId}
  ```
### TaskController

- **Get All Tasks**
  ```http
  GET /api/tasks
  ```
- **Add a Task**
  ```http
  POST /api/tasks
  ```
- **Get Task By ID**
  ```http
  POST /api/tasks/{taskId}
  ```
- **Update Task By ID**
  ```http
  PUT /api/tasks/{projectId}
  ```
- **Delete Task By ID**
  ```http
  DELETE /api/tasks/{projectId}
  ```
### UserController

- **Get Logged-In User**
  ```http
  GET /api/users/current-user
  ```

- **Get All Users**
  ```http
  GET /api/users/
  ```
  
- **Register User**
  ```http
  POST /api/signup
  ```
  
- **Get User By ID**
  ```http
  POST /api/users/{userId}
  ```
  
- **Get User By Username**
  ```http
  GET /api/username/{username}
  ```

- **Update User By ID**
  ```http
  PUT /api/users/{userId}
  ```
  
- **Delete User By ID**
  ```http
  DELETE /api/users/{userId}
  ```
##  Getting Started

To get a local copy of the project up and running, follow this step

### Prerequisites
- Java 11 or later
- Maven or Gradle
- PostgresSQL (or another database of your choice)

### Installation
1. Clone the repository:
  ```bash
  git clone https://github.com/ayushvishwakarma12/project-management-system-api.git
  ```

2. Navigate into the project directory:
  ```bash
  cd project-management-system-api
  ```

3. Configure the application properties:
- Update the src/main/resources/application.properties file with your database credentials and other necessary configurations.

4. Build and run the application:
- Using Maven:
  ```bash
  ./mvnw clean install
  ./mvnw spring-boot:run
  ```

## Authentication

The API uses JWT tokens for authentication. Obtain a token by sending a POST request to /api/signup with your credentials. Include the token in the Authorization header for subsequent requests.