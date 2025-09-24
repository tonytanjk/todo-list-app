# Overengineered ToDo List Application

## Overview

This project is a feature-rich ToDo List web application designed with a modern tech stack, focusing on robust user management, secure authentication, task sharing, and web security best practices.

Key features include:

- User Authentication & Authorization (Registration, Login with JWT)
- Task CRUD operations linked to users
- Task Sharing with permission controls
- Web Security: JWT, BCrypt, CSRF protection, input sanitization
- Responsive React frontend with clean UI/UX
- Backend API with Spring Boot and MyBatis for database interaction
- Microsoft SQL Server database schema

---

## Tech Stack

| Layer        | Technology / Framework             |
|--------------|----------------------------------|
| Frontend     | React, HTML5, CSS3                |
| Backend      | Java 17+, Spring Boot, MyBatis   |
| Database     | Microsoft SQL Server (or any SQL)|
| Security     | Spring Security, JWT, BCrypt      |

---

## Features

### User Management

- User registration with unique username and email validation
- Password hashing with BCrypt
- Login with JWT token issuance
- Role-based access control (e.g., USER, ADMIN)
- Session management via stateless JWT tokens

### Task Management

- Create, Read, Update, Delete (CRUD) operations for tasks
- Each task assigned to a user (ownership)
- Tasks have a unique UUID for tracking
- Completed flag for task status
- Timestamp fields (`created_at`, `updated_at`)

### Task Sharing

- Users can share tasks with other users by specifying target usernames or emails
- Permissions:
  - **Read-only**: Shared users can only view tasks
  - **Read-write**: Shared users can edit and update tasks
- Shared tasks appear in both owner and shared users’ task lists
- Share records tracked in a `Task_Shares` join table

### Web Security

- Password storage with BCrypt hashing
- JWT tokens used for stateless authentication
- CSRF protection enabled on state-changing endpoints
- Input validation and sanitization to mitigate XSS and SQL injection
- Secure API endpoints with role and ownership checks
- HTTPS recommended in production

### UI/UX

- Responsive layout using Flexbox/Grid in CSS
- React functional components with hooks and context API for state management
- Clear validation and error messages on forms
- Intuitive and accessible user interface

---

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Node.js 16 or higher
- Microsoft SQL Server (or compatible RDBMS)
- Maven or Gradle for backend build
- IDE or code editor (IntelliJ IDEA, VS Code, etc.)