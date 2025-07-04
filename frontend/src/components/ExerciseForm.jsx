import React, { useState } from 'react';
import { addExercise } from '../services/api.js';

export function ExerciseForm({ onAdded }) {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [category, setCategory] = useState('');
    const [error, setError] = useState(null);

    const handleSubmit = async e => {
        e.preventDefault();
        try {
            const newEx = await addExercise({ name, description, category });
            onAdded(newEx);
            setName(''); setDescription(''); setCategory('');
        } catch (err) {
            setError(err.message);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Dodaj ćwiczenie</h2>
            {error && <div style={{color:'red'}}>{error}</div>}
            <div>
                <label>Nazwa:<br/><input value={name} onChange={e=>setName(e.target.value)} required /></label>
            </div>
            <div>
                <label>Opis:<br/><input value={description} onChange={e=>setDescription(e.target.value)} /></label>
            </div>
            <div>
                <label>Kategoria:<br/><input value={category} onChange={e=>setCategory(e.target.value)} /></label>
            </div>
            <button type="submit">Dodaj</button>
        </form>
    );
}