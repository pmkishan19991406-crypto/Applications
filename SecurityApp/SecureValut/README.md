# SecureVault App

SecureVault is a full-stack user authentication application developed using React, Spring Boot, and MySQL. It allows users to log in, log out, view their profile, and change their password securely using JWT authentication.

## Technologies Used

- Frontend: React.js, JavaScript, HTML, CSS
- Backend: Spring Boot
- Programming Language: Java 17
- Database: MySQL
- Security: Spring Security and JWT
- ORM: Spring Data JPA and Hibernate
- Build Tool: Maven
- API Communication: REST APIs

## Features

- User Login: Authenticate users using email and password.
- JWT Authentication: Generate and validate JWTs to authenticate users.
- User Logout: Allow users to log out of the application.
- View Profile: Display the authenticated user's profile details.
- Change Password: Allow users to change their password after verifying their current password.
- Password Encryption: Store passwords securely using BCrypt hashing.
- Protected APIs: Restrict access to secured endpoints to authenticated users.
- Database Integration: Store and retrieve user information using MySQL.

## Application Architecture

The application follows a frontend-backend architecture.

- React: Provides the user interface.
- Spring Boot: Handles business logic and REST API requests.
- Spring Security: Manages authentication and API access.
- JWT: Carries authentication information between the frontend and backend.
- Spring Data JPA and Hibernate: Handle database operations.
- MySQL: Stores user account information.

## Authentication Flow

1. The user enters their email and password on the login page.
2. React sends the login request to the Spring Boot backend.
3. The backend verifies the user's credentials.
4. If authentication succeeds, the backend generates a JWT.
5. The frontend sends the JWT with requests to protected APIs.
6. The backend validates the JWT before allowing access.
7. The authenticated user can view their profile and change their password.
8. During logout, the frontend removes the authentication token. Server-side token invalidation can also be implemented if required.

## Database

Database Name: `securevault`

The MySQL database stores user information.

Example user fields:

- `id` – Unique user ID
- `name` – User's full name
- `email` – User's email address
- `password` – BCrypt-hashed password

Passwords are stored as hashes rather than plain text.

## Prerequisites

Install the following software before running the application:

- Java Development Kit (JDK) 17
- Node.js and npm
- MySQL Server
- Maven
- IntelliJ IDEA or Visual Studio Code

## How to Run the Application

### 1. Set Up the Database

Create the MySQL database:

```sql
CREATE DATABASE securevault;
```

### 2. Configure the Backend

Configure your MySQL database URL, username, and password in the Spring Boot `application.properties` file.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/securevault
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Set the database credentials in your environment variables.

### 3. Start the Spring Boot Backend

Open a terminal in the backend project directory and run:

```bash
mvn spring-boot:run
```

The backend typically runs at `http://localhost:8080`.

### 4. Start the React Frontend

Open another terminal in the frontend project directory and run:

```bash
npm install
npm run dev
```

For a Vite-based React project, the frontend typically runs at `http://localhost:5173`.

The actual ports may differ depending on your configuration.

## Security Considerations

- Validate JWT signatures and expiration times.
- Use BCrypt to hash passwords.
- Keep JWT signing secrets and database credentials outside source control.
- Protect private endpoints using Spring Security.
- Configure CORS for trusted frontend origins.
- Use HTTPS in production.
- Revoke or invalidate existing tokens after a password change if required by the application's security policy.

## Learning Objectives

This project helps demonstrate practical knowledge of:

- Java 17 and Spring Boot
- Spring Security and JWT authentication
- REST API development
- React frontend integration
- MySQL database connectivity
- Spring Data JPA and Hibernate
- Password hashing and secure authentication
- Full-stack application development

## Author

Developed as a Java Full Stack project to practice secure user authentication and profile management using React, Spring Boot, and MySQL.

