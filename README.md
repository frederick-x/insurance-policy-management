# Insurance Policy Management System

A comprehensive full-stack web application for managing insurance policies with user authentication, built using Java Spring Boot, MySQL, HTML, CSS, and JavaScript.

![Java](https://img.shields.io/badge/Java-17+-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 🌟 Features

- ✅ **User Authentication** - Secure login and registration system
- ✅ **Dashboard** - Overview with statistics and recent policies
- ✅ **CRUD Operations** - Create, Read, Update, Delete policies
- ✅ **Search & Filter** - Find policies by name or status
- ✅ **Multiple Policy Types** - Life, Health, Vehicle, Property
- ✅ **Status Tracking** - Active, Expired, Cancelled
- ✅ **Responsive Design** - Works on desktop, tablet, and mobile
- ✅ **Real-time Updates** - Dynamic UI without page reload
- ✅ **RESTful API** - Well-structured backend endpoints

## 📸 Screenshots

### Welcome Page
Modern landing page with feature highlights

### Dashboard
Statistics overview with recent policies

### Policy Management
Complete CRUD interface with search and filter

## 🚀 Quick Start

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Modern web browser

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/insurance-policy-management.git
cd insurance-policy-management
```

2. **Setup MySQL Database**
```sql
CREATE DATABASE insurance_db;
CREATE USER 'insurance_user'@'localhost' IDENTIFIED BY 'insurance123';
GRANT ALL PRIVILEGES ON insurance_db.* TO 'insurance_user'@'localhost';
FLUSH PRIVILEGES;
```

3. **Build the application**
```bash
cd backend
mvn clean install -DskipTests
```

4. **Run the application**
```bash
java -jar target/policy-management-1.0.0.jar --spring.datasource.url=jdbc:mysql://localhost:3306/insurance_db --spring.datasource.username=insurance_user --spring.datasource.password=insurance123 --spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

5. **Open the frontend**
   - Open `frontend/welcome.html` in your browser
   - Or visit: `http://localhost:3000` (if using a local server)

## 📁 Project Structure

```
insurance-policy-management/
├── backend/                    # Spring Boot Backend
│   ├── src/main/java/
│   │   └── com/insurance/
│   │       ├── model/         # Entity classes
│   │       ├── repository/    # JPA repositories
│   │       ├── service/       # Business logic
│   │       ├── controller/    # REST controllers
│   │       └── exception/     # Exception handlers
│   └── pom.xml
├── frontend/                   # HTML/CSS/JS Frontend
│   ├── welcome.html           # Landing page
│   ├── login.html             # Login page
│   ├── register.html          # Registration page
│   └── index.html             # Main application
├── console/                    # Core Java Console App
└── README.md
```

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

## 🛠️ Technologies Used

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Web
- MySQL 8.0
- Maven

### Frontend
- HTML5
- CSS3 (Flexbox, Grid)
- JavaScript (ES6+)
- Fetch API

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

## 🎯 Usage

1. **Register** - Create a new account
2. **Login** - Sign in with your credentials
3. **Dashboard** - View statistics and recent policies
4. **Add Policy** - Create new insurance policies
5. **Manage** - Edit or delete existing policies
6. **Search** - Find policies by holder name
7. **Filter** - View policies by status

## 🔐 Security Notes

⚠️ **Important:** This is a demonstration application. For production use:
- Implement password hashing (BCrypt)
- Use JWT tokens for authentication
- Enable HTTPS
- Use environment variables for credentials
- Add rate limiting
- Implement proper input sanitization

## 📝 Documentation

- [Setup Guide](SETUP_GUIDE.md) - Detailed installation instructions
- [Project Summary](PROJECT_SUMMARY.md) - Complete project overview
- [GitHub Setup](GITHUB_SETUP.md) - How to push to GitHub

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Author

**Your Name**
- GitHub: [@YOUR_USERNAME](https://github.com/YOUR_USERNAME)

## 🙏 Acknowledgments

- Spring Boot Documentation
- MySQL Documentation
- Modern UI/UX design principles

## 📞 Support

For issues or questions:
- Open an issue on GitHub
- Check the [Setup Guide](SETUP_GUIDE.md)
- Review the [Project Summary](PROJECT_SUMMARY.md)

---

⭐ Star this repository if you find it helpful!
