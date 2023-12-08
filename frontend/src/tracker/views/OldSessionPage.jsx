import DetailsForm from "../forms/DetailsForm.jsx";
import PastTimeForm from "../forms/PastTimeForm.jsx";
import FeedbackForm from "../forms/FeedbackForm.jsx";
import {useState} from "react";
import SessionSummaryView from "./SessionSummaryView.jsx";

export default function OldSessionPage() {
    const [currentStep, setCurrentStep] = useState(1);

    const updateStep = () => {
        setCurrentStep(currentStep + 1);
    }

    return (
        <>
            {currentStep === 1 && <DetailsForm stepUpdater={updateStep} newSession={false}/>}
            {currentStep === 2 && <PastTimeForm stepUpdater={updateStep}/>}
            {currentStep === 3 && <FeedbackForm stepUpdater={updateStep}/>}
            {currentStep === 4 && <SessionSummaryView />}
        </>
    )
}