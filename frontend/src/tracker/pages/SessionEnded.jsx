import PropTypes from "prop-types";
import {calculateDuration} from "../../utility/calculateDuration.js";
import FeedbackForm from "../forms/FeedbackForm.jsx";
import {useState} from "react";


export default function SessionEnded( {detailsForm, startPeriod, endPeriod} ) {

    const [feedbackForm, setFeedbackForm] = useState({
        distraction: "",
        productivity: 0,
    });

    const duration = calculateDuration(startPeriod, endPeriod);

    function handleFormUpdate(e) {
        setFeedbackForm({
            ...feedbackForm,
            [e.target.name]: e.target.value,
        });
    }

    return (

        <>
            <h2>Session Complete</h2>
            <p>You have worked on {detailsForm.skill} for the past {duration} minute(s)</p>
            <FeedbackForm feedbackForm={feedbackForm} handleFormUpdate={handleFormUpdate}/>
        </>



    )
}


SessionEnded.propTypes = {
    detailsForm: PropTypes.shape({
        skill: PropTypes.string,
        description: PropTypes.string,
    }).isRequired,
    startPeriod: PropTypes.instanceOf(Date).isRequired,
    endPeriod: PropTypes.instanceOf(Date).isRequired
};