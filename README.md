# Finance Data Processing & Access Control System

The Finance Data Processing & Access Control System is a secure, full-stack application designed to manage sensitive financial transactions with precision. It features a modern React dashboard and a robust Spring Boot backend focused on Role-Based Access Control (RBAC).

## 🚀 Key Features

- **Secure Authentication**: JWT-based login and registration with stateless session management.
- **Role-Based Access Control (RBAC)**: Distinct permissions for **Admin** (Full CRUD), **Analyst** (Filter/Read), and **Viewer** (Analytics).
- **Financial Transaction Management**: Complete lifecycle management for income and expense records.
- **Dynamic Dashboard**: Real-time summaries, category breakdowns, and monthly financial trends.
- **Advanced Filtering**: High-performance querying by date range, category, and record type.
- **Context-Aware Persistence**: Transactions are automatically mapped to the authenticated user for auditability.

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot 3.x (Java)
- **Security**: Spring Security (JWT Stateless Auth)
- **Persistence**: Spring Data JPA / Hibernate
- **Database**: PostgreSQL / MySQL (Configurable)
- **Build Tool**: Maven / Wrapper (`mvnw`)

### Frontend
- **Framework**: React 18 (Vite)
- **Styling**: Vanilla CSS (Premium, high-performance UI)
- **API Communication**: Axios (Centralized service layer)
- **Icons/Visuals**: Dynamic charts and metrics

## 📋 Prerequisites

- [Java JDK 17](https://www.oracle.com/java/technologies/downloads/) or later
- [Node.js](https://nodejs.org/) (Version 18 or later)
- [PostgreSQL](https://www.postgresql.org/) or [MySQL](https://www.mysql.com/)

## 🏗️ Getting Started

### 1. Backend Setup
1. Navigate to the backend directory:
   ```bash
   cd Financial_Record
   ```
2. Configure your database credentials in `src/main/resources/application.yml` or `.env`.
3. Start the application using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

### 2. Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd finance-dashboard
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm run dev
   ```

## 📁 Project Structure

- `Financial_Record/`: Contains the Spring Boot backend source code.
  - `src/main/java/com/finance/dashboard/`: Main source package.
    - `controller/`: REST API endpoints.
    - `service/`: Business logic and implementations.
    - `security/`: JWT and RBAC configurations.
    - `entity/`: Database models.
- `finance-dashboard/`: Contains the React/Vite frontend.
  - `src/`: React source code.
    - `components/`: Modular UI components (Dashboard, Modals, Forms).
    - `services/`: API communication layer (`api.js`).
    - `pages/`: Page-level route components.

## 📜 License

This project is for educational purposes as part of the Finance Data Portfolio.
