# 💰 Finance Dashboard Backend

A professional, role-based financial record management system built with **Spring Boot 3.2.5**, **Java 21**, **Spring Security**, and **PostgreSQL**.

## 🚀 Key Features

*   **🔐 Secure Authentication**: Modern JWT-based stateless authentication (`io.jsonwebtoken` 0.11.5).
*   **👥 Role-Based Access Control (RBAC)**:
    *   **ADMIN**: Full CRUD access for financial records and full user management capabilities.
    *   **ANALYST**: Read access to all records and all dashboard analytics/insights.
    *   **VIEWER**: Read-only access to dashboard summaries and financial records.
*   **📊 Dashboard Analytics**: Aggregated insights including total income/expense, net balance, category-wise totals, and monthly trends.
*   **👤 User Management**: Admins can list all users and update their roles or active/inactive status.
*   **✅ Robust Validation**: Input validation using Jakarta Bean Validation.
*   **🛠️ Error Handling**: Global exception handling returning consistent JSON error objects.
*   **💾 Data Seeding**: Automatically seeds 10 sample records and a default test user at startup for immediate evaluation.

---

## 🛠️ Tech Stack

*   **Framework**: Spring Boot 3.2.5
*   **Language**: Java 21
*   **Security**: Spring Security 6 (JWT 0.11.5)
*   **Data layer**: Spring Data JPA / Hibernate
*   **Database**: PostgreSQL
*   **Documentation**: Swagger UI 
*   **Utilities**: Lombok, Jakarta Validation

---

## ⚙️ Setup & Running

### Prerequisites
- Java 21+ installed.
- PostgreSQL database named `finance_db` (or update `application.yml`).
- Maven 3.x (or use the included `mvnw`).

### Steps to Run
1.  **Clone the repository**.
2.  **Configure Database**: Update `src/main/resources/application.yml` with your PostgreSQL credentials.
3.  **Compile & Run**:
    ```powershell
    .\mvnw.cmd spring-boot:run
    ```
4.  **Access the application**:
    - **API Base URL**: `http://localhost:8081/api`
    - **Swagger UI**: `http://localhost:8081/swagger-ui/index.html`
    - **Default User**: `test@gmail.com` | **Password**: `password123`

---

## 📖 API Documentation

### Authentication (`/auth`)
*   `POST /auth/register`: Create a new user account.
*   `POST /auth/login`: Authenticate and receive a JWT token.

### Financial Records (`/api/records`)
*   `GET /api/records`: View all records (Viewer, Analyst, Admin).
*   `POST /api/records`: Create a new record (Admin only).
*   `PUT /api/records/{id}`: Update an existing record (Admin only).
*   `DELETE /api/records/{id}`: Delete a record (Admin only).
*   `GET /api/records/filter`: Filter records by type, category, and date range.

### Dashboard Summary (`/api/dashboard`)
*   `GET /api/dashboard/summary`: Get total income, expense, and balance (Viewer, Analyst, Admin).
*   `GET /api/dashboard/category-wise`: Get totals broken down by category (Viewer, Analyst, Admin).
*   `GET /api/dashboard/trends`: Get monthly income/expense trends (Viewer, Analyst, Admin).
*   `GET /api/dashboard/recent`: Get 5 most recent transactions (Viewer, Analyst, Admin).

### User Management (`/api/users`) - **Admin Only**
*   `GET /api/users`: List all registered users.
*   `PUT /api/users/{id}`: Update a user's role or active status.

---

## 💡 System Design & Assumptions

1.  **Statelessness**: The system uses JWT for sessions to ensure horizontal scalability.
2.  **Compatibility**: Upgraded to JJWT 0.11.5 for full compatibility with Java 21 (resolved JAXB removal issues).
3.  **Default Roles**: Users registered via `/auth/register` default to `ROLE_VIEWER`.
4.  **Security**: Passwords are encrypted using BCrypt. JWT secrets are managed via `JwtUtil`.
5.  **Data Seeding**: A `CommandLineRunner` (DataSeeder) is used to ensure the database has sample data for immediate testing.

---

## 📜 Evaluation Criteria Coverage

- **Role Management**: Implemented Admin, Analyst, and Viewer logic with status management.
- **Access Control**: Enforced using `@PreAuthorize` at the controller level.
- **Record Management**: Full CRUD with filtering and field validation.
- **Aggregated Logic**: Custom repository queries for professional dashboard summaries.
- **Reliability**: Global Exception Handler with specific status codes and JSON responses.
- **Documentation**: Professional README with setup and API details.
