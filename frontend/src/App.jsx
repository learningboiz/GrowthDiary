import './App.css'
import {
    BrowserRouter as Router,
    Routes,
    Route,
    Link
} from "react-router-dom";

export default function App() {
    return (
        <Router>
            <nav>
                <Link to="/">Home</Link>
                <Link to="/new-session">New Session</Link>
                <Link to="/old-session">Old Session</Link>
            </nav>

            <Routes>
                <Route exact path='/' element={<Home />} />
                <Route path='/new-session' element={<NewSession />} />
                <Route path='/old-session' element={<OldSession />} />
            </Routes>
        </Router>
    );
}

function Home() {
    return <h2>Welcome back</h2>
}

function NewSession() {
    return <h2>Start a new session</h2>
}

function OldSession() {
    return <h2>Log a completed session</h2>
}