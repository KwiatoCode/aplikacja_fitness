import React, { useState, useEffect } from 'react';
import { getExercises } from '../services/api.js';
import { ExerciseList } from '../components/ExerciseList';

export default function ExercisesPage() {
    const [exercises, setExercises] = useState([]);
    useEffect(() => {
        getExercises().then(setExercises);
    }, []);

    return (
        <div>
            <h1>Ćwiczenia</h1>
            <ExerciseList exercises={exercises} />
        </div>
    );
}