// src/index.js
import React from 'react';
import ReactDOM from 'react-dom/client';  // Cambiar la importación de ReactDOM
import './index.css';
import App from './App';

// Usa createRoot en lugar de ReactDOM.render
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
