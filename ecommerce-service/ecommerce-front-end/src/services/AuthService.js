// src/services/AuthService.js
import axios from 'axios';

const API_URL = 'http://localhost:9999/api/public';

const login = async (username, password) => {
  try {
    const response = await axios.post(`${API_URL}/login`, { username, password });
    console.log("Login Response:", response.data); // <-- Add this
    localStorage.setItem('token', response.data.token);
    return response.data;
  } catch (error) {
    console.error("Login failed:", error.response?.data || error.message); // <-- Add this
    throw error;
  }
};


// src/services/AuthService.js

const logout = () => {
  localStorage.removeItem('token');  // Remove the token from localStorage
};



const getToken = () => localStorage.getItem('token');

const getUsername = () => localStorage.getItem('username');

const authService = {
  login,
  logout,
  getToken,
  getUsername
};

export default authService;