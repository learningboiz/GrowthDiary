import {useNavigate} from "react-router-dom";
import OutlineButton from "../../components/buttons/OutlineButton.jsx";
import { onAuthStateChanged } from "firebase/auth";
import { auth } from '../../firebase.js';
import {useEffect} from "react";
import LogOut from "../authentication/LogOut.jsx"

export default function HomePage() {

    const navigate = useNavigate();

    const handleSessionNav = (e) => {
        e.preventDefault();
        navigate("/session", {replace: true})
    }

    const handleHistoryNav = (e) => {
        e.preventDefault();
        navigate("/history", {replace: true})
    }

    const handleAnalyticsNav = (e) => {
        e.preventDefault();
        navigate("/analytics", {replace: true})
    }

    return (
        <div className="py-8 px-4 mx-auto max-w-2xl lg:py-16">
            <h2 className="text-2xl font-bold mb-4 text-indigo-600">Welcome back</h2>
            <div className="flex flex-col gap-4 w-full">
                <OutlineButton handleOnClick={handleSessionNav} buttonText={"Start a session"} />
                <OutlineButton handleOnClick={handleHistoryNav} buttonText={"Check session history"} />
                <OutlineButton handleOnClick={handleAnalyticsNav} buttonText={"View analytics"} />
            </div>
            <LogOut />
        </div>
    )
}
