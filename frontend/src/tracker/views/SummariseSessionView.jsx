import {useContext, useState} from "react";
import {SessionContext} from "../SessionContext.jsx";
import sessionAPI from "../../api/sessionAPI.js";
import formatFormForAPI from "../utility/formatFormForAPI.js";
import AfterSessionView from "./AfterSessionView.jsx";
import styles from "../../styles/tracker/sessionReview.module.css"
import {useSessionForm} from "../hooks/useSessionForm.js";

export default function SessionSummaryView() {
    const { sessionForm } = useContext(SessionContext);
    const { submissionMessages, parseFormSummary } = useSessionForm();
    const formSummary = parseFormSummary(sessionForm);

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
                            <td>{formSummary.topic} by {formSummary.description}</td>
                        </tr>
                        <tr>
                            <th>Began on</th>
                            <td>{formSummary.sessionDate} {formSummary.sessionTime}</td>
                        </tr>
                        <tr>
                            <th>Lasted for</th>
                            <td>{formSummary.hours} hours {formSummary.minutes} minutes</td>
                        </tr>
                        <tr>
                            <th>Key obstacle</th>
                            <td>{formSummary.obstacle}</td>
                        </tr>
                        <tr>
                            <th>Productivity</th>
                            <td>{formSummary.productivity}</td>
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
