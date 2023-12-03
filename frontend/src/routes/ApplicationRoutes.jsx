import {Route, Routes} from 'react-router-dom'
import HomePage from "../pages/homepage.jsx";
import AboutPage from "../pages/aboutpage.jsx";
import MainSessionPage from "../pages/tracker/MainSessionPage.jsx";
import FormDetails from "../pages/tracker/FormDetails.jsx";

export default function ApplicationRoutes() {
    return (
        <Routes>
            <Route path="/" element={<HomePage />}></Route>
            <Route path="/about" element={<AboutPage />}></Route>
            <Route path="/session" element={<MainSessionPage />}></Route>
            <Route path="/session/new" element={<FormDetails />}></Route>
        </Routes>
    )
}