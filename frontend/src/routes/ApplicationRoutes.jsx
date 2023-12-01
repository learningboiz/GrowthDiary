import {Route, Routes} from 'react-router-dom'
import HomePage from "../pages/homepage.jsx";
import AboutPage from "../pages/aboutpage.jsx";
import SessionPage from "../pages/sessionpage.jsx";

export default function ApplicationRoutes() {
    return (
        <Routes>
            <Route path="/" element={<HomePage />}></Route>
            <Route path="/about" element={<AboutPage />}></Route>
            <Route path="/session" element={<SessionPage />}></Route>
        </Routes>
    )
}