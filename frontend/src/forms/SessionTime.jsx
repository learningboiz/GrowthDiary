import {useContext, useState} from "react";
import SessionFeedback from "./SessionFeedback.jsx";
import {FormContext} from "./FormContext.jsx";

export default function SessionTime() {
    const { setSessionForm } = useContext(FormContext)
    const [sectionCompleted, setSectionCompleted] = useState(false);

    const [sessionStarted, setSessionStarted] = useState(false);
    const [startPeriod, setStartPeriod] = useState(null);

    const trackSessionStart = () => {

        const startPeriod = new Date();
        setStartPeriod(startPeriod)

        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            startPeriod: startPeriod,
        }))

        setSessionStarted(true)
    }

    const trackSessionEnd = () => {
        const endPeriod = new Date();
        const sessionDuration = Math.round((endPeriod - startPeriod) / 60000);

        setSessionForm((sessionForm) => ({
            ...sessionForm,
            duration: sessionDuration
        }))

        setSectionCompleted(true)
    }


    return (
        <>
            {!sectionCompleted &&
                <>
                    <h2>Step 2</h2>
                    {!sessionStarted && <button onClick={trackSessionStart}>Start</button>}
                    {sessionStarted && <button onClick={trackSessionEnd}>End</button>}
                </>
            }
            {sectionCompleted && <SessionFeedback />}
        </>
    )
}