import {Link} from "react-router-dom";
import {useState} from "react";
import {FormContext} from "./FormContext.jsx";
import SessionDetails from "./SessionDetails.jsx";

export default function BeginSession() {

    const formTemplate = {
        topic:'',
        description:'',
        startPeriod:{},
        duration:'',
        obstacle:'',
        productivity:'',
    }

    const [sessionForm, setSessionForm] = useState(formTemplate);
    const [sessionStarted, setSessionStarted] = useState(false);

    const handleBegin = (e) => {
        e.preventDefault()
        setSessionStarted(true)
    }

    return (
        <>
            <FormContext.Provider value={{sessionForm, setSessionForm}}>
                <>
                    <h2>SESSION TRACKER</h2>
                    {!sessionStarted && <button onClick={handleBegin}>Begin</button>}
                    {sessionStarted && <SessionDetails />}
                </>
            </FormContext.Provider>
        </>
    )
}