import {useContext, useState} from "react";
import FormTime from "./FormTime.jsx";
import {SessionContext} from "./SessionContext.jsx";

export default function FormDetails() {
    const { setSessionForm, resetFormValues } = useContext(SessionContext)
    const [sectionCompleted, setSectionCompleted] = useState(false);

    if (sectionCompleted) {
        return <FormTime />
    }

    const saveData = (e) => {
        resetFormValues();

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