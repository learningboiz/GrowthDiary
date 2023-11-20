import { Routes, Route } from 'react-router-dom'
import HomePage from "./pages/homepage.jsx";
import AboutPage from "./pages/aboutpage.jsx";
import SessionPage from "./pages/sessionpage.jsx";
import NewSessionPage from "./pages/newsessionpage.jsx";
import OldSessionPage from "./pages/oldsessionpage.jsx";
export default function MainRoutes() {
    return (
        <Routes>
            <Route path="/" element={<HomePage />}></Route>
            <Route path="/about" element={<AboutPage />}></Route>
            <Route path="/session" element={<SessionPage />}></Route>
            <Route path="/session/new" element={<NewSessionPage />} />
            <Route path="/session/old" element={<OldSessionPage />} /> {/* Keeping this as a separate path since there's no common components with /session */}
        </Routes>
    )
}