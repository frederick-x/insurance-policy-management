# Insurance Policy Management System - Project Summary

## 📋 Project Overview

A full-stack web application for managing insurance policies with user authentication, built using Java Spring Boot, MySQL, HTML, CSS, and JavaScript.

## 🎯 Project Requirements Met

### ✅ Core Java Console Application
- **Location**: `console/ConsolePolicyManager.java`
- **Features**:
  - Classes and Objects (Policy class)
  - ArrayList and HashMap for data storage
  - CRUD operations
  - Search and filter functionality
  - Input validation
  - Loops and conditional statements

### ✅ Backend (Spring Boot)
- **Framework**: Spring Boot 3.2.0
- **Java Version**: 17+
- **Architecture**: MVC Pattern
- **Components**:
  - **Models**: Policy.java, User.java
  - **Repositories**: PolicyRepository.java, UserRepository.java
  - **Services**: PolicyService.java, UserService.java
  - **Controllers**: PolicyController.java, AuthController.java
  - **Exception Handling**: GlobalExceptionHandler.java

### ✅ Database (MySQL)
- **Database Name**: insurance_db
- **Tables**:
  - `policies` - Stores policy records
  - `users` - Stores user accounts
- **ORM**: Spring Data JPA with Hibernate
- **Configuration**: application.properties

### ✅ Frontend (HTML/CSS/JavaScript)
- **Pages**:
  - `welcome.html` - Landing page
  - `login.html` - User login
  - `register.html` - User registration
  - `index.html` - Main policy management dashboard
- **Features**:
  - Responsive design (Bootstrap/Flexbox)
  - DOM manipulation
  - Event handling
  - Fetch API with async/await
  - JSON data exchange
  - Client-side validation

### ✅ RESTful API Endpoints

**Authentication**:
- POST `/api/auth/register` - Register user
- POST `/api/auth/login` - Login user

**Policies**:
- POST `/api/policies` - Create policy
- GET `/api/policies` - Get all policies
- GET `/api/policies/{id}` - Get policy by ID
- PUT `/api/policies/{id}` - Update policy
- DELETE `/api/policies/{id}` - Delete policy
- GET `/api/policies/search?name={name}` - Search by name
- GET `/api/policies/filter?status={status}` - Filter by status

## 📊 Database Schema

### Policies Table
```sql
CREATE TABLE policies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    policy_holder_name VARCHAR(255) NOT NULL,
    policy_type VARCHAR(50) NOT NULL,
    premium_amount DOUBLE NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_date_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

### Users Table
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_date_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

## 🎨 User Interface Features

### Welcome Page
- Hero section with app description
- Feature highlights (6 cards)
- Call-to-action buttons
- Responsive navigation

### Authentication Pages
- Clean, modern design
- Form validation
- Error/success messages
- Redirect after login/register

### Main Dashboard
- Policy form (Add/Edit)
- Search functionality
- Status filter dropdown
- Policy table with actions
- Visual distinction for expired policies
- User info display with logout

## 🔧 Technologies Used

### Backend
- Java 17+
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Web
- Spring Validation
- MySQL Connector
- Maven

### Frontend
- HTML5
- CSS3 (Flexbox, Grid)
- JavaScript (ES6+)
- Fetch API
- LocalStorage for session

### Database
- MySQL 8.0+

## 📁 File Structure

```
insurance-policy-management/
├── backend/
│   ├── src/main/java/com/insurance/
│   │   ├── InsurancePolicyApplication.java
│   │   ├── model/ (Policy.java, User.java)
│   │   ├── repository/ (PolicyRepository.java, UserRepository.java)
│   │   ├── service/ (PolicyService.java, UserService.java)
│   │   ├── controller/ (PolicyController.java, AuthController.java)
│   │   └── exception/ (GlobalExceptionHandler.java)
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
├── frontend/
│   ├── welcome.html
│   ├── welcome-styles.css
│   ├── login.html
│   ├── register.html
│   ├── auth.js
│   ├── auth-styles.css
│   ├── index.html
│   ├── styles.css
│   └── app.js
├── console/
│   └── ConsolePolicyManager.java
├── README.md
├── SETUP_GUIDE.md
├── PROJECT_SUMMARY.md
└── START_APPLICATION.bat
```

## ✨ Key Features

1. **User Authentication**
   - Registration with validation
   - Login with session management
   - Protected routes

2. **Policy Management**
   - Create new policies
   - View all policies
   - Update existing policies
   - Delete policies
   - Search by holder name
   - Filter by status

3. **Data Validation**
   - Client-side validation (JavaScript)
   - Server-side validation (Spring Validation)
   - Required field checks
   - Format validation

4. **User Experience**
   - Responsive design
   - Real-time updates
   - No page reload for operations
   - Visual feedback (success/error messages)
   - Expired policy highlighting

5. **Security**
   - Session-based authentication
   - CORS configuration
   - Input validation
   - Error handling

## 🚀 How to Run

1. **Setup Database**:
```bash
mysql -u root -p < database_setup.sql
```

2. **Build Backend**:
```bash
cd backend
mvn clean install -DskipTests
```

3. **Start Application**:
```bash
START_APPLICATION.bat
```

Or manually:
```bash
java -jar backend/target/policy-management-1.0.0.jar --spring.datasource.url=jdbc:mysql://localhost:3306/insurance_db --spring.datasource.username=insurance_user --spring.datasource.password=insurance123 --spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

4. **Open Frontend**:
   - Open `frontend/welcome.html` in browser

## 📈 Testing

### Manual Testing Checklist

**Authentication**:
- ✅ Register new user
- ✅ Login with valid credentials
- ✅ Login with invalid credentials
- ✅ Logout functionality

**Policy Operations**:
- ✅ Add new policy
- ✅ View all policies
- ✅ Edit policy
- ✅ Delete policy
- ✅ Search by name
- ✅ Filter by status

**Validation**:
- ✅ Empty field validation
- ✅ Email format validation
- ✅ Password match validation
- ✅ Duplicate username/email check

## 🎓 Learning Outcomes

This project demonstrates:
- Object-Oriented Programming principles
- MVC architecture
- RESTful API design
- Database design and integration
- Frontend-backend integration
- Full-stack development
- User authentication
- CRUD operations
- Responsive web design

## 📝 Future Enhancements

- Password encryption (BCrypt)
- JWT token authentication
- Role-based access control
- Email notifications
- Policy expiry reminders
- File upload for policy documents
- Dashboard analytics
- Export to PDF/Excel
- Multi-language support

## 👥 Credits

**Developer**: Insurance Policy Management Team  
**Version**: 1.0.0  
**Date**: March 8, 2026

---

## 📞 Support

For questions or issues:
- Check `SETUP_GUIDE.md` for detailed setup
- Review `README.md` for quick start
- Check MySQL and backend logs for errors
