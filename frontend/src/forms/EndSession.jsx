import {useContext} from "react";
import {FormContext} from "./FormContext.jsx";
import sendSessionAPIRequest from "../utility/sendSessionAPIRequest.js";
import convertToAPIFormat from "../utility/convertToAPIFormat.js";
import {splitDateTime} from "../utility/splitDateTime.js";
import {splitHourMinute} from "../utility/splitHourMinute.js";

export default function EndSession() {
    const { sessionForm, setSessionForm } = useContext(FormContext)

    const submitForm = (e) => {
        e.preventDefault();
        const finalisedForm = convertToAPIFormat(sessionForm);
        console.log("Session payload: ", finalisedForm)
        sendSessionAPIRequest(finalisedForm);
    }

    const {
        topic,
        description,
        startPeriod,
        duration,
        obstacle,
        productivity
    } = sessionForm;

    const {hours, minutes} = splitHourMinute(duration);
    const formattedDate = startPeriod.toLocaleDateString('en-GB', {day: '2-digit', month: 'short', year: 'numeric'});
    const formattedTime = startPeriod.toLocaleTimeString('en', {hour: 'numeric', minute: 'numeric', hour12:true });

    return (
        <>
            <h3>Here&apos;s a summary of your latest session</h3>
            <div>
                <div>
                    <h4>Worked on</h4>
                    <p>{topic}</p>
                    <p>{description}</p>
                </div>
                <div>
                    <h4>Started on</h4>
                    <p>{formattedDate} {formattedTime}</p>
                </div>
                <div>
                    <h4>Lasted for</h4>
                    <p>{hours} hours {minutes} minutes</p>
                </div>
                <div>
                    <h4>Key challenge</h4>
                    <p>{obstacle}</p>
                </div>
                <div>
                    <h4>Productivity rating</h4>
                    <p>{productivity}</p>
                </div>
            </div>
            <button onClick={submitForm}>Confirm</button>
        </>
    )
}
