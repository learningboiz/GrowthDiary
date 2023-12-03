import {useContext} from "react";
import {SessionContext} from "../SessionContext.jsx";

export default function DetailsForm({stepUpdater}) {
    const { setSessionForm } = useContext(SessionContext)

    const saveData = (e) => {
        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            [e.target.name]: e.target.value
        }))
    }

    const submitForm = (e) => {
        e.preventDefault();
        stepUpdater();
    }

    return (
        <>
            <h2>Step 1</h2>
            <p>Fill in the details below</p>
            <form onSubmit={submitForm}>
                <label>
                    Topic
                    <input
                        type="text"
                        name="topic"
                        onChange={saveData}
                    />
                </label>
                <label>
                    Description
                    <input
                        type="text"
                        name="description"
                        onChange={saveData}
                    />
                </label>
                <button type="submit">Next</button>
            </form>
        </>
    )
}