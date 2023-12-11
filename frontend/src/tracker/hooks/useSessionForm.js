import {useContext} from "react";
import {SessionContext} from "../SessionContext.jsx";
import {productivityDescriptions} from "../utility/productivityDescription.js";

/**
 * Provides methods to handle the saving of user form input and progression through different form components
 */
export function useSessionForm(stepUpdater) {
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

        const productivityDescription = productivityDescriptions[productivity];

        return {
            topic,
            description,
            hours,
            minutes,
            sessionDate,
            sessionTime,
            obstacle,
            productivityDescription
        };
    };

    const submissionMessages = {
        successHeader: "Session complete!",
        successMessage: "Your session has been successfully recorded.",
        errorHeader: "Oops! Something went wrong!"
    }

    return {parseFormSummary, submissionMessages};

}