import {useContext, useState} from "react";
import {SessionContext} from "../SessionContext.jsx";
import sessionAPI from "../../api/sessionAPI.js";
import formatFormForAPI from "../utility/formatFormForAPI.js";
import {splitHourMinute} from "../utility/splitHourMinute.js";
import SubmissionSuccess from "./SubmissionSuccess.jsx";
import SubmissionError from "./SubmissionError.jsx";
import styles from "../../styles/tracker/sessionReview.module.css"

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
                setErrorMessage("There's an issue with the data provided.")
            }
        } catch (error) {
            setErrorMessage("There was an issue with the network.")
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
                <div className={styles.sessionReview}>
                    <h2>Your session summary</h2>
                    <table>
                        <tbody>
                        <tr>
                            <th>Worked on</th>
                            <td>{topic} by {description}</td>
                        </tr>
                        <tr>
                            <th>Began on</th>
                            <td>{formattedDate} {formattedTime}</td>
                        </tr>
                        <tr>
                            <th>Lasted for</th>
                            <td>{hours} hours {minutes} minutes</td>
                        </tr>
                        <tr>
                            <th>Key obstacle</th>
                            <td>{obstacle}</td>
                        </tr>
                        <tr>
                            <th>Productivity</th>
                            <td>{productivity}</td>
                        </tr>
                        </tbody>
                    </table>
                    <button onClick={submitForm}>Confirm</button>
                </div>
            }
            {formSubmitted && <SubmissionSuccess />}
            {errorMessage && <SubmissionError errorMessage={errorMessage} />}
        </>
    )
}
