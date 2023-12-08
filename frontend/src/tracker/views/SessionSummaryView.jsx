import {useContext, useState} from "react";
import {SessionContext} from "../SessionContext.jsx";
import sessionAPI from "../../api/sessionAPI.js";
import formatFormForAPI from "../utility/formatFormForAPI.js";
import AfterSessionView from "./AfterSessionView.jsx";
import styles from "../../styles/tracker/sessionReview.module.css"
import {useFormSubmission} from "../hooks/useFormSubmission.js";

export default function SessionSummaryView() {
    const { sessionForm } = useContext(SessionContext)
    const { submissionMessages, parseFormSummary } = useFormSubmission();
    const { topic, description, hours, minutes, sessionDate, sessionTime, obstacle, productivity} = parseFormSummary(sessionForm);

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
                            <td>{sessionDate} {sessionTime}</td>
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
            {formSubmitted && <AfterSessionView header={submissionMessages.successHeader} message={submissionMessages.successMessage}/>}
            {errorMessage && <AfterSessionView header={submissionMessages.errorHeader} message={errorMessage} />}
        </>
    )
}
