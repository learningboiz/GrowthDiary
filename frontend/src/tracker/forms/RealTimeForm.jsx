import {useContext, useState} from "react";
import {SessionContext} from "../SessionContext.jsx";
import {calculateSessionDuration} from "../utility/calculateSessionDuration.js";

export default function RealTimeForm({stepUpdater}) {
    const { sessionForm, setSessionForm } = useContext(SessionContext)

    const [sessionStarted, setSessionStarted] = useState(false);
    const [startPeriod, setStartPeriod] = useState(null);


    const trackStartTime = () => {

        const startPeriod = new Date();
        setStartPeriod(startPeriod);

        console.log(sessionForm);

        setSessionStarted(true)
    }

    const trackEndTime = () => {
        const endPeriod = new Date();
        const sessionDuration = calculateSessionDuration(startPeriod, endPeriod);

        setSessionForm((sessionForm) => ({
            ...sessionForm,
            startPeriod: startPeriod,
            duration: sessionDuration
        }))

        stepUpdater();
    }


    return (
        <>
            <h2>Step 2</h2>
            {!sessionStarted && <button onClick={trackStartTime}>Start</button>}
            {sessionStarted && <button onClick={trackEndTime}>End</button>}
        </>
    )
}