import PropTypes from "prop-types";
import {useState} from "react";
import SessionEnded from "./SessionEnded.jsx";

export default function SessionStarted({detailsForm, startPeriod} ) {
    const [sessionEnded, setSessionEnded] = useState(false);
    const [endPeriod, setEndPeriod] = useState(null);

    function handleSessionEnd() {
        setSessionEnded(true);
        setEndPeriod(new Date());
    }

    return (
        <>
            {!sessionEnded &&
                <>
                    <h2>Learning session in progress</h2>
                    <p>You have been working on {detailsForm.skill} since {startPeriod.toLocaleString()}</p>
                    <button onClick={handleSessionEnd}>End session</button>
                </>
            }
            {sessionEnded &&
                <SessionEnded
                detailsForm={detailsForm}
                startPeriod={startPeriod}
                endPeriod={endPeriod} />
            }
        </>
    )
}

SessionStarted.propTypes = {
    detailsForm: PropTypes.shape({
        skill: PropTypes.string,
        description: PropTypes.string,
    }).isRequired,
    startPeriod: PropTypes.instanceOf(Date).isRequired
};