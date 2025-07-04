import React from 'react';
import { WeeklySummary } from '../components/WeeklySummary';
import { ScheduleCalendar } from '../components/ScheduleCalendar';

export default function DashboardPage() {
    const userId = 1; // w prawdziwej app pobieraj z kontekstu auth
    const today = new Date().toISOString().slice(0, 10);
    const month = today.slice(0, 7);

    return (
        <div>
            <h1>Dashboard</h1>
            <WeeklySummary userId={userId} start={today} />
            <ScheduleCalendar userId={userId} month={month} />
        </div>
    );
}