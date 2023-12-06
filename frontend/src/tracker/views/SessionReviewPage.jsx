import {useContext, useState} from "react";
import {SessionContext} from "../SessionContext.jsx";
import sessionAPI from "../../api/sessionAPI.js";
import formatFormForAPI from "../utility/formatFormForAPI.js";
import {splitHourMinute} from "../utility/splitHourMinute.js";
import SubmissionSuccess from "./SubmissionSuccess.jsx";
import SubmissionError from "./SubmissionError.jsx";

export default function SessionReviewPage() {
    const { sessionForm } = useContext(SessionContext)
    const [formSubmitted, setFormSubmitted] = useState(false)
    const [errorMessage, setErrorMessage] = useState(null);


    const submitForm = async (e) => {
        e.preventDefault();
        console.log(sessionForm);

        const formattedData = formatFormForAPI(sessionForm);

        try {
            const apiResponse = await sessionAPI(formattedData);
            console.log(apiResponse);
            if (apiResponse.ok) {
                setFormSubmitted(true)
            } else {
                setErrorMessage("There's an issue with the data provided. Please try again later")
            }
        } catch (error) {
            setErrorMessage("There was an issue with the network. Please try again later")
        }
    }

    const {
        topic,
        description,
        startPeriod,
        duration,
        obstacle,
        productivity
    } = sessionForm;

    const [hours, minutes] = splitHourMinute(duration);
    const formattedDate = startPeriod.toLocaleDateString('en-GB', {day: '2-digit', month: 'short', year: 'numeric'});
    const formattedTime = startPeriod.toLocaleTimeString('en', {hour: 'numeric', minute: 'numeric', hour12:true });

    return (
        <>
            {!formSubmitted && !errorMessage &&
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
                            <p>{hours} hour(s) {minutes} minute(s)</p>
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
            }
            {formSubmitted && <SubmissionSuccess />}
            {errorMessage && <SubmissionError errorMessage={errorMessage} />}
        </>
    )
}
