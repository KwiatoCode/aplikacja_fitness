import React, { useState, useEffect } from 'react';
import { getWorkouts } from '../services/api.js';
import { WorkoutForm } from '../components/WorkoutForm';

export default function WorkoutsPage() {
    const [entries, setEntries] = useState([]);
    const userId = 1;

    useEffect(() => {
        getWorkouts(userId, '2025-01-01', '2025-12-31')
            .then(setEntries)
            .catch(console.error);
    }, []);

    const handleAdded = e => setEntries(prev => [...prev, e]);

    return (
        <div>
            <h1>Treningi</h1>
            <WorkoutForm userId={userId} onAdded={handleAdded} />
            <ul>
                {entries.map(e => (
                    <li key={e.id}>
                        {e.date}: {e.exercise.name} ({e.durationMinutes} min)
                    </li>
                ))}
            </ul>
        </div>
    );
}