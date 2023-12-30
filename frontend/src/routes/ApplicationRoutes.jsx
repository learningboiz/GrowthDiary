import {Route, Routes} from 'react-router-dom'
import HomePage from "../features/home/homepage.jsx";
import AboutPage from "../features/home/aboutpage.jsx";
import BeforeSessionView from "../features/sessiontracker/views/BeforeSessionView.jsx";
import NewSessionPage from "../features/sessiontracker/views/NewSessionPage.jsx";
import OldSessionPage from "../features/sessiontracker/views/OldSessionPage.jsx";
import HistoryView from "../features/sessionhistory/HistoryView.jsx";

export default function ApplicationRoutes() {
    return (
        <Routes>
            <Route path="/" element={<HomePage />}></Route>
            <Route path="/about" element={<AboutPage />}></Route>
            <Route path="/session" element={<BeforeSessionView />}></Route>
            <Route path="/session/new" element={<NewSessionPage />}></Route>
            <Route path="/session/old" element={<OldSessionPage />}></Route>
            <Route path="/history" element={<HistoryView />}></Route>
        </Routes>
    )
}