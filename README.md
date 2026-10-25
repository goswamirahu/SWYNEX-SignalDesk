# SignalDesk — Incident Management System

SignalDesk is a Java-based incident management backend application developed as part of my SWYNEX Java Developer Internship.

The application provides REST APIs to create, retrieve, update, and delete incident records using Spring Boot and MySQL.

## Features

- Create and retrieve incident records through REST APIs
- Store incident data in a MySQL database
- Use Spring Data JPA and Hibernate for database operations
- Validate required fields and limit incident title length
- Handle validation errors with structured JSON responses
- Handle missing incident records with appropriate error responses
- Follow a layered architecture using Controller, Service, Repository, and Entity classes

## Technology Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Jakarta Bean Validation
- Maven
- Postman

## Project Structure

```text
com.signaldesk
├── controller
│   └── IncidentController.java
├── entity
│   └── Incident.java
├── exception
│   └── GlobalExceptionHandler.java
├── repository
│   └── IncidentRepository.java
├── service
│   └── IncidentService.java
└── SignaldeskApplication.java
```

## REST API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/incidents` | Create an incident |
| GET | `/api/incidents` | Retrieve all incidents |
| GET | `/api/incidents/{id}` | Retrieve an incident by ID |
| PUT | `/api/incidents/{id}` | Update an incident |
| DELETE | `/api/incidents/{id}` | Delete an incident |

## Incident Fields

Incident records include fields such as ID, title, description, category, priority, and status.

## Testing

The following were tested using Postman and MySQL:

- Creating an incident successfully
- Retrieving incident records
- Rejecting requests with missing required fields
- Confirming that incident data is stored in MySQL

## Database Configuration

Create a MySQL database named `signaldesk_db` and configure the database connection in `application.properties`.

For security, keep database credentials outside the source code by using environment variables.

## Learning Outcomes

This project strengthened my practical understanding of:

- Java backend development
- REST API design
- Spring Boot application architecture
- Database integration using MySQL
- Input validation and exception handling
- Testing APIs using Postman

## Internship

Developed as part of the SWYNEX Java Developer Internship.

## Author

Rahul Giri

GitHub: https://github.com/goswamirahu
