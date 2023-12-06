import {Route, Routes} from 'react-router-dom'
import HomePage from "../pages/homepage.jsx";
import AboutPage from "../pages/aboutpage.jsx";
import MainSessionPage from "../tracker/views/MainSessionPage.jsx";
import DetailsForm from "../tracker/forms/DetailsForm.jsx";
import NewSessionPage from "../tracker/views/NewSessionPage.jsx";
import OldSessionPage from "../tracker/views/OldSessionPage.jsx";

export default function ApplicationRoutes() {
    return (
        <Routes>
            <Route path="/" element={<HomePage />}></Route>
            <Route path="/about" element={<AboutPage />}></Route>
            <Route path="/session" element={<MainSessionPage />}></Route>
            <Route path="/session/new" element={<NewSessionPage />}></Route>
            <Route path="/session/old" element={<OldSessionPage />}></Route>
        </Routes>
    )
}