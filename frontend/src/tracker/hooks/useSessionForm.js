import {useContext} from "react";
import {SessionContext} from "../SessionContext.jsx";

export function useSessionForm(stepUpdater) {
    const { sessionForm, setSessionForm } = useContext(SessionContext);

    const saveInput = (e) => {
        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            [e.target.name]: e.target.value
        }))
    }

    const saveTimeInput = (e) => {
        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            startPeriod: new Date(e.target.value)
        }))
    }

    const saveFormProgress = (e) => {
        e.preventDefault();
        stepUpdater();
    }

    return {saveInput, saveTimeInput, saveFormProgress};
}