import {Route, Routes} from 'react-router-dom'
import HomePage from "./pages/homepage.jsx";
import AboutPage from "./pages/aboutpage.jsx";
import SessionPage from "./pages/sessionpage.jsx";
import DetailsSection from "./forms/detailssection.jsx";
import TimeSection from "./forms/timesection.jsx";
import FeedbackSection from "./forms/feedbacksection.jsx";

export default function Applicationroutes() {
    return (
        <Routes>
            <Route path="/" element={<HomePage />}></Route>
            <Route path="/about" element={<AboutPage />}></Route>
            <Route path="/session" element={<SessionPage />}>
                <Route path="details" element={<DetailsSection />} />
                <Route path="time" element={<TimeSection />} />
                <Route path="feedback" element={<FeedbackSection />} />
            </Route>
        </Routes>
    )
}