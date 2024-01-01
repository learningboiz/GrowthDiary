import React from 'react';
import { Navigate, Route } from 'react-router-dom';
import { useAuth } from '../features/authentication/AuthContext.jsx';

const ProtectedRoute = ({ children }) => {
    const { user } = useAuth();

    if (!user) {
        // Redirect to log-in if the user is not authenticated
        return <Navigate to="/" replace />;
    }

    return children
};

export default ProtectedRoute;