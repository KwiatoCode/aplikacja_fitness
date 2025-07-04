import React, { useState } from 'react';
import { ScheduleCalendar } from '../components/ScheduleCalendar';
import { WorkoutForm } from '../components/WorkoutForm';

export default function SchedulePage() {
    const userId = 1;
    const [month, setMonth] = useState(
        new Date().toISOString().slice(0, 7)
    );

    return (
        <div>
            <h1>Harmonogram</h1>
            <WorkoutForm userId={userId} onAdded={() => {}} />
            <ScheduleCalendar userId={userId} month={month} />
        </div>
    );
}