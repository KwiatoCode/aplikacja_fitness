export const API_BASE = '/api';

// Ćwiczenia
export async function getExercises() {
    const res = await fetch(`${API_BASE}/exercises`);
    if (!res.ok) throw new Error('Błąd ładowania ćwiczeń');
    return res.json();
}

export async function addExercise(ex) {
    const res = await fetch(`${API_BASE}/exercises`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(ex)
    });
    if (!res.ok) throw new Error('Błąd dodawania ćwiczenia');
    return res.json();
}

// Wpisy treningowe
export async function getWorkouts(userId, from, to) {
    const url = new URL(`${API_BASE}/users/${userId}/workouts`, window.location.origin);
    url.searchParams.set('from', from);
    url.searchParams.set('to', to);
    const res = await fetch(url);
    if (!res.ok) throw new Error('Błąd ładowania wpisów treningu');
    return res.json();
}

export async function addWorkout(userId, entry) {
    const res = await fetch(`${API_BASE}/users/${userId}/workouts`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(entry)
    });
    if (!res.ok) throw new Error('Błąd dodawania wpisu treningu');
    return res.json();
}

// Podsumowania
export async function getWeeklySummary(userId, start) {
    const res = await fetch(`${API_BASE}/users/${userId}/workouts/summary/week?start=${start}`);
    if (!res.ok) throw new Error('Błąd pobierania podsumowania');
    return res.json();
}

// Harmonogram
export async function getSchedule(userId, month) {
    const res = await fetch(`${API_BASE}/users/${userId}/schedule?month=${month}`);
    if (!res.ok) throw new Error('Błąd pobierania harmonogramu');
    return res.json();
}

// Użytkownik
export async function getUser(userId) {
    const res = await fetch(`${API_BASE}/users/${userId}`);
    if (!res.ok) throw new Error('Błąd ładowania użytkownika');
    return res.json();
}

export async function createUser(user) {
    const res = await fetch(`${API_BASE}/users`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    });
    if (!res.ok) throw new Error('Błąd tworzenia użytkownika');
    return res.json();
}

export async function updateUser(userId, user) {
    const res = await fetch(`${API_BASE}/users/${userId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    });
    if (!res.ok) throw new Error('Błąd aktualizacji użytkownika');
    return res.json();
}