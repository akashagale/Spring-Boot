// src/components/ProtectedRoute.js
import React from 'react';
import { Navigate } from 'react-router-dom';
import AuthService from '../services/AuthService';

const ProtectedRoute = ({ children }) => {
  const token = AuthService.getToken();
  return token ? children : <Navigate to="/login" />;
};

export default ProtectedRoute;
