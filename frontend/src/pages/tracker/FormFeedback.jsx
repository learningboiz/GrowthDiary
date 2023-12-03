import {useContext, useState} from "react";
import {SessionContext} from "./SessionContext.jsx";
import SessionReviewPage from "./SessionReviewPage.jsx";

export default function FormFeedback() {
    const { setSessionForm } = useContext(SessionContext)
    const [sectionCompleted, setSectionCompleted] = useState(false);

    if (sectionCompleted) {
        return <SessionReviewPage />
    }

    const saveData = (e) => {
        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            [e.target.name]: e.target.value
        }))
    }

    const submitForm = (e) => {
        e.preventDefault();
        setSectionCompleted(true)
    }

    return (
        <>
            <h2>Step 3</h2>
            <p>Provide feedback for the session</p>
            <form onSubmit={submitForm}>
                <label>
                    Key Obstacle
                    <input
                        type="text"
                        name="obstacle"
                        onChange={saveData}
                    />
                </label>
                <label>
                    Productivity
                    <input
                        type="range"
                        min="1"
                        max="5"
                        step="1"
                        name="productivity"
                        onChange={saveData}
                    />
                </label>
                <button type="submit">Complete</button>
            </form>
        </>
    )
}