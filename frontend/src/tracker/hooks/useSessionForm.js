import {useContext} from "react";
import {SessionContext} from "../SessionContext.jsx";

/**
 * Provides methods to handle the saving of user form input and progression through different form components
 * @returns {{saveFormProgress: saveFormProgress, saveTimeInput: saveTimeInput, saveInput: saveInput}}
 */
export function useSessionForm(stepUpdater) {
    const { setSessionForm } = useContext(SessionContext);

    /**
     * Registers data from user input field into the respective sessionForm attribute
     */
    const saveInput = (e) => {
        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            [e.target.name]: e.target.value
        }))
    }

    /**
     * Registers time data by creating a data based on user input
     * Created a separate method since date creation is required
     */
    const saveTimeInput = (e) => {
        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            startPeriod: new Date(e.target.value)
        }))
    }

    /**
     * Updates the form stepper to allow the correct order of form component rendering
     */
    const saveFormProgress = (e) => {
        e.preventDefault();
        stepUpdater();
    }

    return {saveInput, saveTimeInput, saveFormProgress};
}