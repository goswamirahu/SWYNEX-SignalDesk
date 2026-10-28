# SignalDesk Pro — Advanced Incident Management System

SignalDesk Pro is a Java-based incident management REST API built with Spring Boot and MySQL. It enables users to create, view, update, delete, search, and filter incidents, with support for pagination, sorting, input validation, and dashboard statistics.

This project demonstrates object-oriented programming principles, layered application architecture, RESTful API development, relational database integration, and backend exception handling.

## Features

- **Incident Management (CRUD):** Create, retrieve, update, and delete incidents.
- **Search:** Search incidents by title or description using a keyword.
- **Status Filtering:** Retrieve incidents by status, such as OPEN or RESOLVED.
- **Priority Filtering:** Filter incidents by priority, such as HIGH, MEDIUM, or LOW.
- **Category Filtering:** Retrieve incidents by category.
- **Pagination:** Retrieve records in manageable pages.
- **Sorting:** Sort paginated incidents by supported fields in ascending or descending order.
- **Dashboard Statistics:** View total incidents, open incidents, resolved incidents, and high-priority incidents.
- **Input Validation:** Validate required fields and title length.
- **Exception Handling:** Handle invalid requests and incidents that cannot be found.
- **MySQL Persistence:** Store incident records in a relational database.

## Technology Stack

- Java 22
- Spring Boot 4.1.2
- Spring Web
- Spring Data JPA
- Hibernate ORM
- MySQL 9.2
- Jakarta Bean Validation
- Maven
- IntelliJ IDEA
- Postman
- Git and GitHub

## Project Architecture

The application follows a layered architecture to separate API handling, business logic, and database access.

```text
src/main/java/com/signaldesk/
├── controller/
│   └── IncidentController.java
├── entity/
│   └── Incident.java
├── repository/
│   └── IncidentRepository.java
├── service/
│   └── IncidentService.java
└── exception/
    └── GlobalExceptionHandler.java
```

- **Controller:** Exposes REST endpoints and handles HTTP requests.
- **Service:** Implements incident management, searching, filtering, pagination, and statistics logic.
- **Repository:** Uses Spring Data JPA to communicate with MySQL.
- **Entity:** Represents incident records stored in the database.
- **Exception Handler:** Provides centralized exception handling where configured.

## Prerequisites

Install the following before running the application:

- Java 22 JDK
- MySQL Server
- Maven, or the Maven Wrapper included in the project
- IntelliJ IDEA or another Java IDE
- Postman for API testing

## Database Configuration

Create the database in MySQL:

```sql
CREATE DATABASE signaldesk_db;
```

Configure the database connection in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/signaldesk_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Set the `DB_USERNAME` and `DB_PASSWORD` environment variables to your local MySQL credentials before starting the application.

Keep database passwords and other secrets out of public source control.

Hibernate can create or update the required table structure when the application starts with the configuration above.

## Run the Application

1. Clone the repository:

```bash
git clone https://github.com/goswamirahu/SWYNEX-SignalDesk.git
```

2. Open the project in IntelliJ IDEA.
3. Configure the MySQL database and environment variables.
4. Run the main Spring Boot application class, `SignaldeskApplication`.
5. The API will be available at:

```text
http://localhost:8080
```

## REST API Endpoints

Base URL: `http://localhost:8080/api/incidents`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/incidents` | Create an incident |
| GET | `/api/incidents` | Retrieve all incidents |
| GET | `/api/incidents/{id}` | Retrieve an incident by ID |
| PUT | `/api/incidents/{id}` | Update an incident |
| DELETE | `/api/incidents/{id}` | Delete an incident |
| GET | `/api/incidents/search?keyword=login` | Search by title or description |
| GET | `/api/incidents/filter/status?status=OPEN` | Filter by status |
| GET | `/api/incidents/filter/priority?priority=HIGH` | Filter by priority |
| GET | `/api/incidents/filter/category?category=Authentication` | Filter by category |
| GET | `/api/incidents/page?page=0&size=5&sortBy=id&direction=desc` | Paginate and sort incidents |
| GET | `/api/incidents/dashboard` | Retrieve dashboard statistics |

## Sample API Request

### Create an Incident

**Request**

```http
POST http://localhost:8080/api/incidents
Content-Type: application/json
```

**Request Body**

```json
{
  "title": "Login API failure",
  "description": "Users are unable to log in to the application",
  "category": "Authentication",
  "priority": "HIGH",
  "status": "OPEN"
}
```

A successful creation returns HTTP `201 Created` with the saved incident details.

### Search Incidents

```http
GET http://localhost:8080/api/incidents/search?keyword=login
```

Returns incidents whose titles or descriptions contain the supplied keyword.

### Dashboard Statistics

```http
GET http://localhost:8080/api/incidents/dashboard
```

Example response structure:

```json
{
  "totalIncidents": 5,
  "openIncidents": 3,
  "resolvedIncidents": 1,
  "highPriorityIncidents": 2
}
```

The values above are illustrative for a database containing the corresponding five records. Actual values depend on the current database contents.

### Pagination and Sorting

```http
GET http://localhost:8080/api/incidents/page?page=0&size=5&sortBy=id&direction=desc
```

- `page`: Zero-based page number.
- `size`: Number of records per page, from 1 to 100.
- `sortBy`: Supported fields include `id`, `title`, `category`, `priority`, and `status`.
- `direction`: `asc` or `desc`.

## Validation and Error Handling

The application uses Jakarta Bean Validation to enforce required incident fields and the maximum title length. The service layer also handles incidents that cannot be found and validates pagination parameters.

Requests should be checked using Postman to verify successful responses and appropriate error handling.

## Database Verification

Incident records can be inspected directly in MySQL:

```sql
USE signaldesk_db;

SELECT * FROM incidents;

SELECT COUNT(*) AS total_incidents
FROM incidents;
```

These queries help verify that incident records are persisted in the database.

## Testing

The API has been exercised using Postman for incident management and the added search, filtering, pagination, sorting, and dashboard endpoints. MySQL queries can be used to verify stored records and dashboard-related counts.

## Learning Outcomes

This project demonstrates practical experience with:

- Java object-oriented programming
- Spring Boot REST API development
- Layered backend architecture
- MySQL database integration
- Spring Data JPA and Hibernate
- Input validation and exception handling
- Searching, filtering, pagination, and sorting
- API testing with Postman
- Git and GitHub version control

## Future Enhancements

- Authentication and role-based authorization
- Incident history and audit logging
- Automated unit and integration tests
- Swagger/OpenAPI documentation
- A web-based dashboard

## Author

**Rahul Giri**

Java Backend Developer | Spring Boot | REST APIs | MySQL

GitHub: https://github.com/goswamirahu

## Project Repository

https://github.com/goswamirahu/SWYNEX-SignalDesk
