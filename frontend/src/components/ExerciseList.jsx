import React from 'react';

export function ExerciseList({ exercises }) {
    if (!exercises.length) return <p>Brak ćwiczeń.</p>;
    return (
        <ul>
            {exercises.map(e => (
                <li key={e.id}>{e.name} ({e.category})</li>
            ))}
        </ul>
    );
}