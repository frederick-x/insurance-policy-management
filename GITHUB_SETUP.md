# GitHub Setup Guide

## Step-by-Step Instructions to Push to GitHub

### 1. Create a GitHub Repository

1. Go to https://github.com
2. Click the "+" icon in the top right
3. Select "New repository"
4. Fill in:
   - Repository name: `insurance-policy-management`
   - Description: `Full-stack Insurance Policy Management System with Spring Boot, MySQL, and JavaScript`
   - Choose Public or Private
   - **DO NOT** initialize with README (we already have one)
5. Click "Create repository"

### 2. Initialize Git in Your Project

Open Command Prompt or PowerShell in your project directory and run:

```bash
git init
```

### 3. Add All Files to Git

```bash
git add .
```

### 4. Create Your First Commit

```bash
git commit -m "Initial commit: Insurance Policy Management System"
```

### 5. Add Remote Repository

Replace `YOUR_USERNAME` with your GitHub username:

```bash
git remote add origin https://github.com/YOUR_USERNAME/insurance-policy-management.git
```

### 6. Push to GitHub

```bash
git branch -M main
git push -u origin main
```

### 7. Enter GitHub Credentials

When prompted:
- Username: Your GitHub username
- Password: Use a Personal Access Token (not your password)

#### How to Create a Personal Access Token:

1. Go to GitHub Settings → Developer settings → Personal access tokens → Tokens (classic)
2. Click "Generate new token (classic)"
3. Give it a name: "Insurance Policy Management"
4. Select scopes: Check "repo" (full control of private repositories)
5. Click "Generate token"
6. **Copy the token immediately** (you won't see it again)
7. Use this token as your password when pushing

## Alternative: Using GitHub Desktop

If you prefer a GUI:

1. Download GitHub Desktop: https://desktop.github.com/
2. Install and sign in
3. Click "Add" → "Add existing repository"
4. Select your project folder
5. Click "Publish repository"
6. Choose public/private and click "Publish"

## Verify Your Upload

After pushing, visit:
```
https://github.com/YOUR_USERNAME/insurance-policy-management
```

You should see all your files!

## Future Updates

To push changes later:

```bash
git add .
git commit -m "Description of changes"
git push
```

## Common Issues

### Issue: "fatal: remote origin already exists"
**Solution:**
```bash
git remote remove origin
git remote add origin https://github.com/YOUR_USERNAME/insurance-policy-management.git
```

### Issue: Authentication failed
**Solution:** Make sure you're using a Personal Access Token, not your password

### Issue: Large files
**Solution:** The .gitignore file already excludes large files like JARs and build folders

## What Gets Pushed

✅ Source code (Java, HTML, CSS, JS)
✅ Configuration files (pom.xml, application.properties)
✅ Documentation (README, guides)
✅ Console application

❌ Compiled files (target/, *.class)
❌ IDE files (.idea/, *.iml)
❌ Build artifacts (*.jar)
❌ Sensitive data (passwords in plain text)

## Security Note

⚠️ **Important:** The application.properties file contains database credentials. For production:
1. Use environment variables
2. Add application.properties to .gitignore
3. Create application.properties.example with placeholder values

## Repository Description

Use this for your GitHub repository description:

```
Full-stack Insurance Policy Management System built with Java Spring Boot, MySQL, HTML, CSS, and JavaScript. Features include user authentication, CRUD operations, search & filter, and a modern responsive UI.
```

## Topics/Tags to Add

Add these topics to your repository for better discoverability:
- java
- spring-boot
- mysql
- javascript
- html-css
- full-stack
- insurance
- crud-application
- rest-api
- web-application
