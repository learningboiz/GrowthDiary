import {useContext, useState} from "react";
import SessionTime from "./SessionTime.jsx";
import {FormContext} from "./FormContext.jsx";

export default function SessionDetails() {
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
            }
            {sectionCompleted && <SessionTime />}
        </>
    )
}