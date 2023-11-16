import DetailsForm from "../forms/DetailsForm.jsx";
import SessionStarted from "./SessionStarted.jsx";
import {useState} from "react";
export default function MainSessionPage() {
    const [sessionStarted, setSessionStarted] = useState(false);
    const [startPeriod, setStartPeriod] = useState(null);
    const [detailsForm, setDetailsForm] = useState({
        skill: "",
        description: "",
    });

    function handleFormUpdate(e) {
        setDetailsForm({
            ...detailsForm,
            [e.target.name]: e.target.value,
        });
    }

    function handleSessionStart() {
        setSessionStarted(true);
        setStartPeriod(new Date());
    }

    return (
        <>
            {!sessionStarted &&
                <>
                    <h2>What are you working on today</h2>
                    <DetailsForm
                        detailsForm={detailsForm}
                        handleFormUpdate={handleFormUpdate}
                    />
                    <button onClick={handleSessionStart}>Begin</button>
                </>
            }
            {sessionStarted &&
                <SessionStarted
                    detailsForm={detailsForm}
                    startPeriod={startPeriod}/>
            }
        </>
    )
}