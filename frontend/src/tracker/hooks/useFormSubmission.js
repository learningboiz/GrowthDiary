import {splitHourMinute} from "../utility/splitHourMinute.js";

export function useFormSubmission() {

    const submissionMessages = {
        successHeader: "Session complete!",
        successMessage: "Your session has been successfully recorded.",
        errorHeader: "Oops!Something went wrong!"
    }

    const parseFormSummary = (sessionForm) => {

        const {
            topic,
            description,
            startPeriod,
            duration,
            obstacle,
            productivity
        } = sessionForm;

        const [hours, minutes] = splitHourMinute(duration);
        const sessionDate = startPeriod.toLocaleDateString('en-GB', {day: '2-digit', month: 'short', year: 'numeric'});
        const sessionTime = startPeriod.toLocaleTimeString('en', {hour: 'numeric', minute: 'numeric', hour12:true });

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

    return {submissionMessages, parseFormSummary};
}
