import {useContext, useState} from "react";
import {SessionContext} from "../SessionContext.jsx";

/**
 * Provides methods to handle the saving of user form input and progression through different form components
 */
export function useSessionForm(stepUpdater) {
    const {sessionForm, setSessionForm} = useContext(SessionContext);

    const parseFormSummary = (sessionForm) => {

        const {
            topic,
            description,
            hours,
            minutes,
            startPeriod,
            obstacle,
            productivity
        } = sessionForm;

        const sessionDate = startPeriod.toLocaleDateString('en-GB', {
            day: '2-digit',
            month: 'short',
            year: 'numeric'
        });
        const sessionTime = startPeriod.toLocaleTimeString('en', {
            hour: 'numeric',
            minute: 'numeric',
            hour12: true
        });

        return {
            topic,
            description,
            hours,
            minutes,
            sessionDate,
            sessionTime,
            obstacle,
            productivity
        };
    };

    const submissionMessages = {
        successHeader: "Session complete!",
        successMessage: "Your session has been successfully recorded.",
        errorHeader: "Oops! Something went wrong!"
    }

    return {parseFormSummary, submissionMessages};

}