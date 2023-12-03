import {useContext, useState} from "react";
import FormFeedback from "./FormFeedback.jsx";
import {SessionContext} from "./SessionContext.jsx";

export default function FormTime() {
    const { sessionForm, setSessionForm } = useContext(SessionContext)
    const [sectionCompleted, setSectionCompleted] = useState(false);

    const [sessionStarted, setSessionStarted] = useState(false);
    const [startPeriod, setStartPeriod] = useState(null);

    if (sectionCompleted) {
        return <FormFeedback />
    }

    const trackStartTime = () => {

        const startPeriod = new Date();
        setStartPeriod(startPeriod);

        console.log(sessionForm);

        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            startPeriod: startPeriod,
        }))

        setSessionStarted(true)
    }

    const trackEndTime = () => {
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
            <h2>Step 2</h2>
            {!sessionStarted && <button onClick={trackStartTime}>Start</button>}
            {sessionStarted && <button onClick={trackEndTime}>End</button>}
        </>
    )
}