import {useContext, useState} from "react";
import {SessionContext} from "../SessionContext.jsx";
import {getDurationInHoursMinutes} from "../utility/getDurationInHoursMinutes.js";

export default function RealTimeForm({stepUpdater}) {
    const { setSessionForm } = useContext(SessionContext)

    const [sessionStarted, setSessionStarted] = useState(false);
    const [startPeriod, setStartPeriod] = useState(null);

    const subHeadingText = sessionStarted? "Session has begun" : "Click to start the session"


    const trackStartTime = () => {

        const startPeriod = new Date();
        setStartPeriod(startPeriod);

        setSessionStarted(true)
    }

    const trackEndTime = () => {
        const endPeriod = new Date();
        const [hours, minutes] = getDurationInHoursMinutes(startPeriod, endPeriod);

        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            startPeriod: startPeriod,
            hours: hours,
            minutes: minutes
        }))

        stepUpdater();
    }


    return (
        <div>
            <h2>Time</h2>
            <h3>{subHeadingText}</h3>
            <form>
                {!sessionStarted && <button onClick={trackStartTime}>Start</button>}
                {sessionStarted && <button onClick={trackEndTime}>End</button>}
            </form>
        </div>
    )
}