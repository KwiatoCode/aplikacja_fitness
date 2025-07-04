import React, { useState, useEffect } from 'react';
import { getUser, updateUser } from '../services/api.js';

export default function ProfilePage() {
    const userId = 1;
    const [user, setUser] = useState(null);
    const [form, setForm] = useState({ username: '', email: '' });

    useEffect(() => {
        getUser(userId).then(u => {
            setUser(u);
            setForm({ username: u.username, email: u.email });
        }).catch(console.error);
    }, []);

    const handleChange = e => {
        const { name, value } = e.target;
        setForm(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = e => {
        e.preventDefault();
        updateUser(userId, form)
            .then(setUser)
            .catch(console.error);
    };

    if (!user) return <p>Loading...</p>;

    return (
        <div>
            <h1>Profil</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>
                        Username:<br/>
                        <input
                            name="username"
                            value={form.username}
                            onChange={handleChange}
                            required
                        />
                    </label>
                </div>
                <div>
                    <label>
                        Email:<br/>
                        <input
                            name="email"
                            type="email"
                            value={form.email}
                            onChange={handleChange}
                            required
                        />
                    </label>
                </div>
                <button type="submit">Zapisz zmiany</button>
            </form>
        </div>
    );
}