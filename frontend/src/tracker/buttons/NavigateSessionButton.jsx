import {useNavigate} from "react-router-dom";
import {useContext} from "react";
import {SessionContext} from "../SessionContext.jsx";
import styles from "../../styles/tracker/sessionButton.module.css";

export default function NavigateSessionButton({newSession}) {

    const navigate = useNavigate();
    const {resetFormValues} = useContext(SessionContext);

    const buttonText = newSession ? "Start a new session" : "Record a past session";
    const path = newSession ? "/session/new" : "/session/old";


    const startSession = (e) => {
        e.preventDefault();
        resetFormValues();
        navigate(path, {replace: true})
    }

    return <button className={styles.sessionButton}
        onClick={startSession}>{buttonText}</button>
}