# Insurance Policy Management System - Setup Guide

## 📁 Project Structure

```
insurance-policy-management/
├── backend/                           # Spring Boot Backend
│   ├── src/main/java/com/insurance/
│   │   ├── InsurancePolicyApplication.java
│   │   ├── model/
│   │   │   ├── Policy.java
│   │   │   └── User.java
│   │   ├── repository/
│   │   │   ├── PolicyRepository.java
│   │   │   └── UserRepository.java
│   │   ├── service/
│   │   │   ├── PolicyService.java
│   │   │   └── UserService.java
│   │   ├── controller/
│   │   │   ├── PolicyController.java
│   │   │   └── AuthController.java
│   │   └── exception/
│   │       └── GlobalExceptionHandler.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
├── frontend/                          # HTML/CSS/JS Frontend
│   ├── welcome.html                   # Landing page
│   ├── welcome-styles.css
│   ├── login.html                     # Login page
│   ├── register.html                  # Registration page
│   ├── auth.js                        # Auth JavaScript
│   ├── auth-styles.css                # Auth styles
│   ├── index.html                     # Main app (Policy Management)
│   ├── styles.css                     # Main app styles
│   └── app.js                         # Main app JavaScript
├── console/                           # Core Java Console App
│   └── ConsolePolicyManager.java
└── README.md
```

## 🔧 Prerequisites

1. **Java 17 or higher**
   - Download: https://www.oracle.com/java/technologies/downloads/

2. **Maven 3.6+**
   - Download: https://maven.apache.org/download.cgi
   - Add to PATH: `C:\Program Files\Apache\maven\bin`

3. **MySQL 8.0+**
   - Download: https://dev.mysql.com/downloads/mysql/
   - Ensure MySQL service is running

4. **Modern Web Browser**
   - Chrome, Firefox, Edge, or Safari

## 📦 Database Setup

### Step 1: Create MySQL User and Database

```sql
-- Login to MySQL as root
mysql -u root -p

-- Create database
CREATE DATABASE IF NOT EXISTS insurance_db;

-- Create user (if not exists)
CREATE USER IF NOT EXISTS 'insurance_user'@'localhost' IDENTIFIED WITH mysql_native_password BY 'insurance123';

-- Grant privileges
GRANT ALL PRIVILEGES ON insurance_db.* TO 'insurance_user'@'localhost';
FLUSH PRIVILEGES;

-- Use the database
USE insurance_db;

-- Create tables
CREATE TABLE IF NOT EXISTS policies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    policy_holder_name VARCHAR(255) NOT NULL,
    policy_type VARCHAR(50) NOT NULL,
    premium_amount DOUBLE NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_date_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_date_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

## 🚀 Running the Application

### Option 1: Using Maven (Recommended)

1. **Build the application:**
```bash
cd backend
mvn clean install -DskipTests
```

2. **Run the backend:**
```bash
java -jar target/policy-management-1.0.0.jar --spring.datasource.url=jdbc:mysql://localhost:3306/insurance_db?createDatabaseIfNotExist=true --spring.datasource.username=insurance_user --spring.datasource.password=insurance123 --spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver --spring.jpa.hibernate.ddl-auto=update
```

3. **Open the frontend:**
   - Open `frontend/welcome.html` in your browser
   - Or use a local server:
   ```bash
   cd frontend
   python -m http.server 3000
   ```
   Then visit: http://localhost:3000/welcome.html

### Option 2: Using IDE (IntelliJ/Eclipse/VS Code)

1. **Import the backend project**
   - Open `backend` folder as Maven project
   - Wait for dependencies to download

2. **Update application.properties** (if needed)
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/insurance_db?createDatabaseIfNotExist=true
   spring.datasource.username=insurance_user
   spring.datasource.password=insurance123
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
   server.port=8080
   ```

3. **Run InsurancePolicyApplication.java**
   - Right-click → Run

4. **Open frontend/welcome.html** in browser

## 📱 Using the Application

### 1. Welcome Page
- Visit `welcome.html`
- Learn about the application features
- Click "Get Started" to register or "Login" to sign in

### 2. Registration
- Fill in username, email, and password
- Click "Register"
- Redirected to login page

### 3. Login
- Enter username and password
- Click "Login"
- Redirected to main policy management page

### 4. Policy Management
- **Add Policy**: Fill form and click "Add Policy"
- **View Policies**: See all policies in the table
- **Search**: Type holder name in search box
- **Filter**: Select status from dropdown
- **Edit**: Click yellow "Edit" button
- **Delete**: Click red "Delete" button
- **Logout**: Click "Logout" in header

## 🔌 API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user

### Policies
- `POST /api/policies` - Create policy
- `GET /api/policies` - Get all policies
- `GET /api/policies/{id}` - Get policy by ID
- `PUT /api/policies/{id}` - Update policy
- `DELETE /api/policies/{id}` - Delete policy
- `GET /api/policies/search?name={name}` - Search by name
- `GET /api/policies/filter?status={status}` - Filter by status

## 🐛 Troubleshooting

### Backend won't start
- Check MySQL is running: `mysql -u insurance_user -pinsurance123`
- Verify port 8080 is not in use
- Check Java version: `java -version`

### Database connection error
- Verify MySQL credentials
- Check database exists: `SHOW DATABASES;`
- Ensure user has privileges

### Frontend not connecting to backend
- Verify backend is running on port 8080
- Check browser console for errors
- Ensure CORS is enabled (already configured)

## 📝 Console Application

To run the Core Java console version:

```bash
cd console
javac ConsolePolicyManager.java
java ConsolePolicyManager
```

Features:
- Add, view, update, delete policies
- Search by name
- Filter by status
- Uses ArrayList and HashMap

## 🔐 Security Notes

**Important**: This is a demonstration application. For production:
- Implement password hashing (BCrypt)
- Add JWT token authentication
- Use HTTPS
- Implement input sanitization
- Add rate limiting
- Use environment variables for credentials

## 📊 Database Verification

Check data in MySQL:
```sql
-- View all policies
SELECT * FROM insurance_db.policies;

-- View all users
SELECT * FROM insurance_db.users;

-- Count records
SELECT COUNT(*) FROM insurance_db.policies;
```

## 💾 Backup Your Data

```bash
# Backup database
mysqldump -u insurance_user -pinsurance123 insurance_db > backup.sql

# Restore database
mysql -u insurance_user -pinsurance123 insurance_db < backup.sql
```

## 🎯 Features Checklist

- ✅ User Registration & Login
- ✅ Session Management
- ✅ CRUD Operations for Policies
- ✅ Search by Policy Holder Name
- ✅ Filter by Status
- ✅ Visual distinction for expired policies
- ✅ Form validation (client & server)
- ✅ Responsive design
- ✅ Real-time updates without page reload
- ✅ MySQL database persistence
- ✅ RESTful API
- ✅ Exception handling

## 📞 Support

For issues or questions:
1. Check the troubleshooting section
2. Verify all prerequisites are installed
3. Check MySQL and backend logs
4. Review browser console for frontend errors

---

**Version**: 1.0.0  
**Last Updated**: March 8, 2026
