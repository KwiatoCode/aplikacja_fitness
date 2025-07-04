import React, { useState, useEffect } from 'react';
import { getWeeklySummary } from '../services/api.js';

export function WeeklySummary({ userId, start }) {
    const [total, setTotal] = useState(null);
    useEffect(() => { getWeeklySummary(userId, start).then(setTotal).catch(console.error); }, [userId, start]);
    return <div>Łączny czas w tygodniu od {start}: {total ?? '…'} min</div>;
}