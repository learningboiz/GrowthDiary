import {useContext, useState} from "react";
import {FormContext} from "./FormContext.jsx";
import EndSession from "./EndSession.jsx";

export default function SessionFeedback() {
    const { setSessionForm } = useContext(FormContext)
    const [sectionCompleted, setSectionCompleted] = useState(false);


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
            {!sectionCompleted &&
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
                                name="productivity"
                                onChange={saveData}
                            />
                        </label>
                        <button type="submit">Complete</button>
                    </form>
                </>
            }
            {sectionCompleted && <EndSession />}
        </>
    )
}