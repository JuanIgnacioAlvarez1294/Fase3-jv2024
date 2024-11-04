// src/components/BackOffice.js
import React from 'react';

const BackOffice = () => {
    // Example user list (this can later be replaced with actual data)
    const users = [
        { id: 1, username: 'juan', role: 'ROLE_ADMIN' },
        { id: 2, username: 'nico', role: 'ROLE_USER' },
    ];

    return (
        <div>
            <h1>Admin Back Office</h1>
            <h2>User Management</h2>
            <ul>
                {users.map((user) => (
                    <li key={user.id}>
                        {user.username} - {user.role}
                    </li>
                ))}
            </ul>
            <button>Add New User</button>
            {/* Implement functionality to add/edit/delete users later */}
        </div>
    );
};

export default BackOffice;

