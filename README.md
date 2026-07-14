# Expense Tracker Backend API 🚀

A secure and scalable Expense Tracker Backend API built using Spring Boot, Spring Security, JWT Authentication,
Hibernate/JPA, and MySQL.

This project follows a professional layered architecture and provides secure multi-user financial management features
including:

* Expense Management
* Income Management
* Debt Management
* Loan Management
* JWT Authentication & Authorization
* Pagination, Sorting & Filtering APIs
* Swagger/OpenAPI Documentation

---

# 📌 Project Highlights

## 🔐 Authentication & Security

* JWT-based Authentication
* Secure Login & Registration APIs
* BCrypt Password Encryption
* Stateless Authentication
* Spring Security Integration
* Ownership-Based Authorization
* Secure Protected APIs
* Swagger JWT Authorization Support

---

## 💰 Expense Management

* Create Expense
* Get Current User Expenses
* Get Single Expense
* Update Expense
* Delete Expense
* Payment Mode Filtering
* Keyword Search
* Pagination & Sorting

---

## 💵 Income Management

* Secure JWT-Based CRUD APIs
* Ownership Validation
* Income Category Support
* Secure Multi-User Access

---

## 📉 Debt Management

* Debt CRUD APIs
* Debt Status & Priority
* Due Date Tracking
* Ownership Validation
* Secure Access Control

---

## 📈 Loan Management

* Loan CRUD APIs
* Loan Type & Status
* Recurring Loan Support
* Ownership Validation
* Secure JWT Access

---

# 🛠️ Tech Stack

## Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate ORM
* JWT Authentication
* Maven

---

## Database

* MySQL

---

## API Documentation

* Swagger OpenAPI

---

# 🏗️ Architecture

This project follows a layered architecture:

```text
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

---

## Additional Architectural Components

```text
DTO Layer
JWT Security Layer
Global Exception Handling
Swagger/OpenAPI Documentation
Validation Layer
```

---

# 🔐 JWT Security Flow

```text
Client Request
    ↓
JWT Authentication Filter
    ↓
SecurityContextHolder
    ↓
Authenticated User
    ↓
Ownership Validation
    ↓
Protected Resource Access
```

---

# 📁 Project Structure

```text
src/main/java/com/abdulmajid/expensetracker

├── config
├── controller
├── dto
│   ├── request
│   └── response
├── enums
├── exception
├── model
├── repository
├── security
│   ├── jwt
│   └── utils
├── service
│   └── impl
└── validation
```

---

# 🔑 Authentication APIs

## Register User

```http
POST /auth/register
```

---

## Login User

```http
POST /auth/login
```

### Login Response

```json
{
  "token": "JWT_TOKEN",
  "message": "User logged in successfully"
}
```

---

# 💰 Expense APIs

| Method | Endpoint                   |
|--------|----------------------------|
| POST   | `/expenses`                |
| GET    | `/expenses/me`             |
| GET    | `/expenses/me/{expenseId}` |
| PUT    | `/expenses/{expenseId}`    |
| DELETE | `/expenses/{expenseId}`    |

---

# 💵 Income APIs

| Method | Endpoint                 |
|--------|--------------------------|
| POST   | `/incomes`               |
| GET    | `/incomes/me`            |
| GET    | `/incomes/me/{incomeId}` |
| PUT    | `/incomes/{incomeId}`    |
| DELETE | `/incomes/{incomeId}`    |

---

# 📉 Debt APIs

| Method | Endpoint             |
|--------|----------------------|
| POST   | `/debts`             |
| GET    | `/debts/me`          |
| GET    | `/debts/me/{debtId}` |
| PUT    | `/debts/{debtId}`    |
| DELETE | `/debts/{debtId}`    |

---

# 📈 Loan APIs

| Method | Endpoint             |
|--------|----------------------|
| POST   | `/loans`             |
| GET    | `/loans/me`          |
| GET    | `/loans/me/{loanId}` |
| PUT    | `/loans/{loanId}`    |
| DELETE | `/loans/{loanId}`    |

---

# 📄 Pagination, Sorting & Filtering

## Pagination Example

```http
GET /expenses/me?page=0&size=5
```

---

## Sorting Example

```http
GET /expenses/me?sortBy=date&sortDir=desc
```

---

## Filter By Payment Mode

```http
GET /expenses/me?paymentMode=UPI
```

---

## Search By Keyword

```http
GET /expenses/me?keyword=tea
```

---

## Combined Filtering Example

```http
GET /expenses/me?paymentMode=UPI&keyword=tea
```

---

# 🧪 Swagger API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Swagger Features

* Interactive API Testing
* JWT Authorization Support
* Request/Response Testing
* API Documentation

---

# ⚙️ Setup & Run

## Clone Repository

```bash
git clone <YOUR_REPOSITORY_URL>
```

---

## Configure Database

Update:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=root
spring.datasource.password=your_password
```

---

## Run Application

```bash
mvn spring-boot:run
```

---

# 🔒 Authorization Header Example

```http
Authorization: Bearer YOUR_JWT_TOKEN
```

---

# 🧠 Key Backend Concepts Implemented

* JWT Authentication
* Authorization
* Ownership Validation
* Stateless Security
* Secure CRUD APIs
* DTO Mapping
* Global Exception Handling
* Pageable APIs
* Filtering & Searching
* Swagger JWT Integration
* Enum-Based Modeling
* Layered Architecture

---

# 🚀 Future Improvements

* Refresh Token Support
* Role-Based Authorization
* React Frontend Integration
* Dashboard Analytics
* Export Reports
* Docker Deployment
* Cloud Deployment
* Email Notifications

---

# 👨‍💻 Developer

## Abdul Majid

Backend Developer focused on:

* Java Backend Development
* Spring Boot APIs
* Secure JWT Authentication
* REST API Design
* Scalable Backend Architecture

---

# ⭐ Project Status

* ✅ Active Development
* ✅ Secure JWT-Based Architecture
* ✅ Multi-User Financial APIs
* ✅ Swagger JWT Integration Completed
* ✅ Pagination, Sorting & Filtering Implemented
* ✅ Frontend Integration Planned
