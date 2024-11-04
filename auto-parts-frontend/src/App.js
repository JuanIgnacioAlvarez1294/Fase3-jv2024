import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import Cart from './components/Cart'; // Your cart component
import BackOffice from './components/BackOffice'; // Your back office component

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/cart" element={<Cart />} />
                <Route path="/backoffice" element={<BackOffice />} />
            </Routes>
        </Router>
    );
};

export default App;
