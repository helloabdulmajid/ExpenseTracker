# Expense Tracker Backend API

A professional backend REST API for an Expense Tracker application built using Spring Boot.

This project focuses on clean backend architecture, secure authentication, scalable API design, and real-world backend
development practices.

---

# Tech Stack

* Java 17
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Swagger / OpenAPI
* Lombok

---

# Features

## Authentication & Security

* User signup
* User login
* BCrypt password hashing
* JWT token generation
* Stateless authentication architecture
* Spring Security integration

## Expense Management

* Create expense
* Update expense
* Delete expense
* Get all expenses for a user
* Get expense by ID
* Daily expense filtering
* Weekly expense filtering
* Monthly expense filtering

## Income Management

* Create income
* Update income
* Delete income
* Get user incomes

## Debt Management

* Create debt records
* Track debt status
* Debt priority handling
* Recurring debt support

## Loan Management

* Create loan records
* Loan type handling
* Loan status tracking
* Remaining balance support

## Category System

### Default Categories

System-generated categories:

* FOOD
* TRANSPORT
* HEALTH
* SALARY
* FREELANCING
* etc.

### User Categories

Users can create custom:

* Expense categories
* Income categories

---

# Project Architecture

The project follows layered architecture.

```text
Controller
→ Service
→ ServiceImpl
→ Repository
→ Entity
```

Additional architecture patterns:

* DTO-based request/response handling
* Centralized exception handling
* JWT security layer
* RESTful API design
* Helper methods for reusable logic
* Swagger API documentation

---

# Package Structure

```text
src/main/java/com/abdulmajid/expensetracker
│
├── auth
│   ├── controller
│   ├── dto
│   └── service
│
├── config
│
├── controller
│
├── dto
│   ├── request
│   └── response
│
├── enums
│
├── exception
│   ├── custom
│   └── handler
│
├── model
│   └── base
│
├── repository
│
├── security
│   └── jwt
│
├── service
│   └── impl
│
└── ExpenseTrackerApplication
```

---

# Swagger API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger is used for:

* API testing
* Request/response documentation
* Validation visibility
* Frontend integration support

---

# Authentication Flow

## Signup

```text
POST /users/signup
```

Passwords are encrypted using BCrypt before storing in database.

---

## Login

```text
POST /auth/login
```

After successful login:

* Credentials are verified
* JWT token is generated
* Token is returned to client

Example response:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "message": "User logged in successfully"
}
```

---

# Security Concepts Used

* Stateless authentication
* JWT token generation
* Password hashing
* Spring Security filter chain
* Authentication vs Authorization
* REST API security principles

---

# Exception Handling

Centralized exception handling using:

* `@RestControllerAdvice`
* Custom exceptions
* Structured API error responses

Examples:

* UserNotFoundException
* ExpenseNotFoundException
* InvalidEnumException
* CategoryNotFoundException

---

# Future Improvements

Planned enhancements:

* JWT authorization filter
* Role-based authentication
* Refresh tokens
* Pagination & sorting
* Dashboard analytics
* React frontend integration
* Docker deployment
* AWS deployment
* Unit & integration testing
* CI/CD pipeline

---

# Learning Goals of This Project

This project is being built to learn and practice:

* Backend architecture
* Spring Boot best practices
* REST API design
* Spring Security
* JWT Authentication
* Clean code principles
* Real-world backend development

---

# Author

Abdul Majid

GitHub:

[https://github.com/helloabdulmajid](https://github.com/helloabdulmajid)
