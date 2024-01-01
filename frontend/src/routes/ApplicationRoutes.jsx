import {Route, Routes} from 'react-router-dom'
import Signup from "../features/authentication/Signup.jsx";
import Login from "../features/authentication/Login.jsx"
import HomePage from "../features/home/homepage.jsx";
import BeforeSessionView from "../features/sessiontracker/views/BeforeSessionView.jsx";
import NewSessionPage from "../features/sessiontracker/views/NewSessionPage.jsx";
import OldSessionPage from "../features/sessiontracker/views/OldSessionPage.jsx";
import HistoryView from "../features/sessionhistory/HistoryView.jsx";
import AnalyticsView from "../features/sessionanalytics/AnalyticsView.jsx";
import ProtectedRoute from "./ProtectedRoute.jsx";
export default function ApplicationRoutes() {


    return (
        <Routes>
            <Route path="*" element={<h2>Hello</h2>}></Route>

            <Route path="/" element={<Login />}></Route>
            <Route path="/signup" element={<Signup />}></Route>

            <Route path="/home" element={
                <ProtectedRoute>
                    <HomePage />
                </ProtectedRoute>
                }></Route>

            <Route path="/session" element={
                <ProtectedRoute>
                    <BeforeSessionView />
                </ProtectedRoute>
                }></Route>

            <Route path="/session/new" element={
                <ProtectedRoute>
                    <NewSessionPage />
                </ProtectedRoute>
                }></Route>

            <Route path="/session/old" element={
                <ProtectedRoute>
                    <OldSessionPage />
                </ProtectedRoute>
                }></Route>

            <Route path="/history" element={
                <ProtectedRoute>
                    <HistoryView />
                </ProtectedRoute>
                }></Route>

            <Route path="/analytics" element={
                <ProtectedRoute>
                    <AnalyticsView />
                </ProtectedRoute>
                }></Route>
        </Routes>
    )
}