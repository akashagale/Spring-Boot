// src/components/Header.js
import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <div style={styles.header}>
      <h2 style={styles.logo}>My Store</h2>
      <Link to="/login" style={styles.login}>Login</Link>
    </div>
  );
};

const styles = {
  header: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: '10px 20px',
    backgroundColor: '#f8f8f8',
    borderBottom: '1px solid #eee',
  },
  logo: {
    margin: 0,
    fontSize: '24px',
    fontWeight: 'bold',
    color: '#333',
  },
  login: {
    textDecoration: 'none',
    fontWeight: 'bold',
    color: '#007bff',
    padding: '8px 15px',
    borderRadius: '5px',
    backgroundColor: '#e9ecef',
  },
};

export default Header;