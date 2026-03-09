@echo off
echo ========================================
echo Insurance Policy Management System
echo ========================================
echo.

echo Starting Backend Server...
cd backend
start cmd /k "java -jar target/policy-management-1.0.0.jar --spring.datasource.url=jdbc:mysql://localhost:3306/insurance_db?createDatabaseIfNotExist=true --spring.datasource.username=insurance_user --spring.datasource.password=insurance123 --spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver --spring.jpa.hibernate.ddl-auto=update"

echo Waiting for backend to start...
timeout /t 10 /nobreak

echo Opening Frontend...
cd ..
start frontend/welcome.html

echo.
echo ========================================
echo Application Started Successfully!
echo Backend: http://localhost:8080
echo Frontend: Check your browser
echo ========================================
pause
