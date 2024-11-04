// src/components/Login.js
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('USER'); // Default role for registration
    const [registerMode, setRegisterMode] = useState(false); // To toggle between login and register
    const navigate = useNavigate();

    const decodeToken = (token) => {
        try {
            const payload = token.split('.')[1];
            const decoded = JSON.parse(atob(payload));
            console.log("Decoded JWT:", decoded); // Log the decoded token for debugging
            return decoded; // Ensure this contains the user's role
        } catch (error) {
            console.error("Failed to decode token:", error);
            return {};
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (registerMode) {
            // Handle registration
            const response = await fetch('http://localhost:8080/api/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, password, role }),
            });

            if (response.ok) {
                alert('User registered successfully!'); // Optional: Show success message
                setRegisterMode(false); // Switch back to login mode
            } else {
                alert('Registration failed');
            }
        } else {
            // Handle login
            const response = await fetch('http://localhost:8080/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, password }),
            });

            if (response.ok) {
                const token = await response.text(); // Assuming the token is returned as text
                localStorage.setItem('token', token);

                // Decode the token to get the user role
                const userRole = decodeToken(token).role; // Use the decodeToken function
                console.log("User Role:", userRole); // Log the user role for debugging

                // Check if the userRole is correct and navigate accordingly
                if (userRole === 'ROLE_ADMIN') { // Make sure this matches exactly
                    navigate('/backoffice'); // Redirect to back office for admin
                } else {
                    navigate('/cart'); // Redirect to cart for regular users
                }
            } else {
                alert('Login failed');
            }
        }
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    placeholder="Username"
                    required
                />
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Password"
                    required
                />
                {registerMode && (
                    <select 
                        value={role} 
                        onChange={(e) => setRole(e.target.value)}
                    >
                        <option value="USER">User</option>
                        <option value="ADMIN">Admin</option>
                    </select>
                )}
                <button type="submit">{registerMode ? 'Register' : 'Login'}</button>
                <button type="button" onClick={() => setRegisterMode(!registerMode)}>
                    {registerMode ? 'Back to Login' : 'Register'}
                </button>
            </form>
        </div>
    );
};

export default Login;

