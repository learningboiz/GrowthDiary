import {useContext, useState} from "react";
import {SessionContext} from "../SessionContext.jsx";
import {calculateDuration} from "../utility/calculateDuration.js";
import styles from "../../styles/tracker/sessionForm.module.css"

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
        const sessionDuration = calculateDuration(startPeriod, endPeriod);

        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            startPeriod: startPeriod,
            duration: sessionDuration
        }))

        stepUpdater();
    }


    return (
        <div className={styles.sessionForm}>
            <h2>Time</h2>
            <h3>{subHeadingText}</h3>
            <form>
                {!sessionStarted && <button onClick={trackStartTime}>Start</button>}
                {sessionStarted && <button onClick={trackEndTime}>End</button>}
            </form>
        </div>
    )
}