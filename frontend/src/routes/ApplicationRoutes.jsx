import {Route, Routes} from 'react-router-dom'
import HomePage from "../pages/homepage.jsx";
import AboutPage from "../pages/aboutpage.jsx";
import BeforeSessionView from "../tracker/views/BeforeSessionView.jsx";
import DetailsForm from "../tracker/forms/DetailsForm.jsx";
import NewSessionPage from "../tracker/views/NewSessionPage.jsx";
import OldSessionPage from "../tracker/views/OldSessionPage.jsx";
import HistoryView from "../history/HistoryView.jsx";

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