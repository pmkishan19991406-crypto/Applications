Employee Ticket Management System
This is a backend application developed using Java, Spring Boot, Spring Security, Spring Data JPA, Hibernate, MySQL, and REST APIs. The system is designed to manage employee support tickets with secure login and role-based authorization. Employees can create and track their own tickets, while administrators can view all tickets and update their status.

Project Overview
The Employee Ticket Management System provides a simple workflow for handling tickets between employees and administrators.

Employee flow:
Employees register, log in, create tickets, and track their status. Tickets start with the status OPEN and can be viewed by the employee who created them.

Admin flow:
Administrators log in, view all tickets, and update their status. Tickets progress through the statuses IN_PROGRESS, RESOLVED, and CLOSED.

Technologies Used
Java 17
Spring Boot 4.0.8
Spring Security
Spring Data JPA
Hibernate
MySQL
Maven
REST APIs
Postman
Eclipse IDE

User Roles
Employee:

Register an account

Login securely

Create support tickets

View their own tickets

Track ticket status

Admin:

Login securely

View all employee tickets

Update ticket status

Manage ticket progress

Main Features
User registration

BCrypt password encryption

Session-based authentication

Role-based authorization with ADMIN and EMPLOYEE roles

Role-based login redirection

Employee-specific ticket viewing

Automatic employee username assignment

Ticket priority management

Ticket status management

Global exception handling

RESTful APIs

MySQL database integration

Postman API testing

Ticket Status Flow
OPEN → IN_PROGRESS → RESOLVED → CLOSED

Ticket Priorities
LOW → MEDIUM → HIGH

Project Structure
EmployeeTicketApp

src/main/java/com/kishan

config: SecurityConfig.java

controller: UserController.java, TicketController.java, AdminTicketController.java, RoleController.java

entity: User.java, Ticket.java

enums: Role.java, Priority.java, TicketStatus.java

repository: IUserRepository.java, ITicketRepository.java

service: IUserService.java, UserServiceImpl.java, ITicketService.java, TicketServiceImpl.java

security: MyUserDetailsService.java

exception: TicketNotFoundException.java, GlobalExceptionHandler.java

src/main/resources/application.properties

pom.xml

README.md

Database Configuration
Create a MySQL database:
CREATE DATABASE abc;

Configure the database in src/main/resources/application.properties. Example:

spring.datasource.url=jdbc:mysql://localhost:3306/abc
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080

Replace YOUR_MYSQL_PASSWORD with your local MySQL password. Do not upload your real database password to GitHub.

API Endpoints
User APIs:

POST /users → Register a user

GET /users → Get all users

GET /users/{id} → Get user by ID

PUT /users → Update a user

DELETE /users/{id} → Delete a user

Authentication APIs:

POST /login → Login

POST /logout → Logout

Employee APIs:

GET /employee/dashboard → Employee dashboard

POST /tickets → Create new ticket (status = OPEN, username auto-set)

GET /tickets/my → View tickets for logged-in employee

Admin APIs:

GET /admin/dashboard → Admin dashboard

GET /admin/tickets → View all tickets

PUT /admin/tickets/{id}/status?status=IN_PROGRESS → Update ticket status

