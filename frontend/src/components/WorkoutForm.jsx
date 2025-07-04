import React, { useState, useEffect } from 'react';
import { getExercises, addWorkout } from '../services/api.js';

export function WorkoutForm({ userId, onAdded }) {
    const [exercises, setExercises] = useState([]);
    const [exerciseId, setExerciseId] = useState('');
    const [date, setDate] = useState('');
    const [duration, setDuration] = useState('');
    const [error, setError] = useState(null);

    useEffect(() => { getExercises().then(setExercises).catch(console.error); }, []);

    const handleSubmit = async e => {
        e.preventDefault();
        try {
            const newEntry = await addWorkout(userId, { exercise: { id: Number(exerciseId) }, date,	durationMinutes: Number(duration) });
            onAdded(newEntry);
        } catch (err) { setError(err.message); }
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Dodaj wpis treningu</h2>
            {error && <div style={{color:'red'}}>{error}</div>}
            <div>
                <label>Ćwiczenie:<br/>
                    <select value={exerciseId} onChange={e=>setExerciseId(e.target.value)} required>
                        <option value="">-- wybierz --</option>
                        {exercises.map(e=> <option key={e.id} value={e.id}>{e.name}</option>)}
                    </select>
                </label>
            </div>
            <div><label>Data:<input type="date" value={date} onChange={e=>setDate(e.target.value)} required /></label></div>
            <div><label>Czas (min):<input type="number" value={duration} onChange={e=>setDuration(e.target.value)} required /></label></div>
            <button type="submit">Zapisz</button>
        </form>
    );
}