# Employee Expense  Tracker

A full-stack web application developed to manage employee expense submissions and reimbursement approvals.

## Technologies Used
- Java 17
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
- React.js
- JavaScript
- HTML and CSS
- Maven

## Features
- Employee registration and login.
- Manager and employee role management.
- Employees can submit expenses.
- Employees can view their expense history.
- Managers can view all employee expenses.
- Managers can approve or reject expenses.
- Employees can track expense status.
- Manager role can be transferred to another employee.
- JWT-based authentication.
- Password encryption using BCrypt.
- Role-based authorization.

## Application Workflow
1. The first registered user becomes the Manager.
2. Subsequent users are registered as Employees.
3. Employees submit expense requests.
4. The Manager reviews expense requests.
5. The Manager approves or rejects requests.
6. Employees can view the updated status.

## Database
MySQL is used to store user and expense information.

## How to Run

### Backend
1. Configure MySQL database details in `application.properties`.
2. Configure the JWT secret key.
3. Run the Spring Boot application.

### Frontend
1. Open the frontend folder.
2. Run `npm install`.
3. Run `npm run dev`.

## Author
Kishan P M