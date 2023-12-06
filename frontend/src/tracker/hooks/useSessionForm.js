import {useContext} from "react";
import {SessionContext} from "../SessionContext.jsx";

export function useSessionForm(stepUpdater) {
    const { setSessionForm } = useContext(SessionContext);

    const saveInput = (e) => {
        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            [e.target.name]: e.target.value
        }))
    }

    const saveFormProgress = (e) => {
        e.preventDefault();
        stepUpdater();
    }

    return {saveInput, saveFormProgress};
}