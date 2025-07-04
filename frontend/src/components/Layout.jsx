import React from 'react';
import { Link } from 'react-router-dom';

export default function Layout({ children }) {
    return (
        <div style={{ display: 'flex' }}>
            <nav style={{ width: 200, padding: '1rem', background: '#222', color: '#fff' }}>
                <h3>Menu</h3>
                <ul style={{ listStyle: 'none', padding: 0 }}>
                    <li><Link to="/dashboard">Dashboard</Link></li>
                    <li><Link to="/exercises">Ćwiczenia</Link></li>
                    <li><Link to="/workouts">Treningi</Link></li>
                    <li><Link to="/schedule">Harmonogram</Link></li>
                    <li><Link to="/profile">Profil</Link></li>
                </ul>
            </nav>
            <main style={{ flex: 1, padding: '1rem' }}>
                {children}
            </main>
        </div>
    );
}