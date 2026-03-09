const API_URL = 'http://localhost:8080/api/auth';

// Login Form Handler
const loginForm = document.getElementById('login-form');
if (loginForm) {
    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        
        try {
            const response = await fetch(`${API_URL}/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password })
            });
            
            const data = await response.json();
            
            if (response.ok) {
                // Store user info in localStorage
                localStorage.setItem('user', JSON.stringify(data));
                showMessage('Login successful! Redirecting...', 'success');
                
                setTimeout(() => {
                    window.location.href = 'index.html';
                }, 1500);
            } else {
                showMessage(data.error || 'Login failed', 'error');
            }
        } catch (error) {
            showMessage('Connection error. Please try again.', 'error');
        }
    });
}

// Register Form Handler
const registerForm = document.getElementById('register-form');
if (registerForm) {
    registerForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const username = document.getElementById('username').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm-password').value;
        
        if (password !== confirmPassword) {
            showMessage('Passwords do not match', 'error');
            return;
        }
        
        try {
            const response = await fetch(`${API_URL}/register`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, email, password })
            });
            
            const data = await response.json();
            
            if (response.ok) {
                showMessage('Registration successful! Redirecting to login...', 'success');
                
                setTimeout(() => {
                    window.location.href = 'login.html';
                }, 2000);
            } else {
                showMessage(data.error || 'Registration failed', 'error');
            }
        } catch (error) {
            showMessage('Connection error. Please try again.', 'error');
        }
    });
}

function showMessage(message, type) {
    const messageDiv = document.getElementById('message');
    messageDiv.textContent = message;
    messageDiv.className = `message ${type}`;
    
    if (type === 'error') {
        setTimeout(() => {
            messageDiv.className = 'message';
        }, 5000);
    }
}
