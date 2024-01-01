import {useContext, useState} from "react";
import {SessionContext} from "../SessionContext.jsx";
import {getDurationInHoursMinutes} from "../utility/getDurationInHoursMinutes.js";
import OutlineButton from "../../../components/buttons/OutlineButton.jsx";

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
        <div className="py-8 px-4 mx-auto max-w-2xl lg:py-16">
            <h2 className="text-2xl font-bold mb-4 text-indigo-600">Time</h2>
            <h3 className="text-base -mt-2 mb-6 text-gray-600 border-gray-300 border-b pb-3">{subHeadingText}</h3>
            <form>
                {!sessionStarted && <OutlineButton handleOnClick={trackStartTime} buttonText={"Start"}></OutlineButton>}
                {sessionStarted && <OutlineButton handleOnClick={trackEndTime} buttonText={"End"}></OutlineButton>}
            </form>
        </div>
    )
}