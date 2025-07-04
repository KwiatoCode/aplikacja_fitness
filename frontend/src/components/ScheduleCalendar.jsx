import React, { useState, useEffect } from 'react';
import { Calendar, dateFnsLocalizer } from 'react-big-calendar';
import { getSchedule } from '../services/api.js';
import { parseISO, format } from 'date-fns';
import 'react-big-calendar/lib/css/react-big-calendar.css';

const locales = { 'en-US': require('date-fns/locale/en-US') };
const localizer = dateFnsLocalizer({ format, parse: parseISO, startOfWeek: () => new Date(), getDay: date => date.getDay(), locales });

export function ScheduleCalendar({ userId, month }) {
    const [events, setEvents] = useState([]);
    useEffect(() => {
        getSchedule(userId, month).then(data => {
            setEvents(data.map(w => ({ title: 'Plan', start: parseISO(w.date), end: parseISO(w.date) })));
        }).catch(console.error);
    }, [userId, month]);

    return (
        <Calendar
            localizer={localizer}
            events={events}
            startAccessor="start"
            endAccessor="end"
            style={{ height: 500 }}
        />
    );
}