import './App.css'
import MainSessionPage from './tracker/pages/MainSessionPage.jsx'
import {
    BrowserRouter as Router,
    Routes,
    Route,
    Link
} from "react-router-dom";
import SessionPageButton from "./tracker/SessionPageButton.jsx";

export default function App() {
    return (
        <Router>
            <nav>
                <Link to="/">Home</Link>
                <Link to="/session">Start Session</Link>
            </nav>

            <Routes>
                <Route exact path='/' element={<Home />} />
                <Route path='/session' element={<MainSessionPage />} />
            </Routes>
        </Router>
    );
}

function Home() {
    return (
        <>
            <h2>Welcome back</h2>
            <SessionPageButton />
        </>

    )
}