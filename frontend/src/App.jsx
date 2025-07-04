import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Layout from './components/Layout';
import DashboardPage from './pages/DashboardPage';
import ExercisesPage from './pages/ExercisesPage';
import WorkoutsPage from './pages/WorkoutsPage';
import SchedulePage from './pages/SchedulePage';
import ProfilePage from './pages/ProfilePage';

function App() {
    return (
        <Layout>
            <Routes>
                <Route path="/" element={<Navigate to="/dashboard" replace />} />
                <Route path="/dashboard" element={<DashboardPage />} />
                <Route path="/exercises" element={<ExercisesPage />} />
                <Route path="/workouts" element={<WorkoutsPage />} />
                <Route path="/schedule" element={<SchedulePage />} />
                <Route path="/profile" element={<ProfilePage />} />
                <Route path="*" element={<h2>404 - Nie ma takiej strony</h2>} />
            </Routes>
        </Layout>
    );
}

export default App;