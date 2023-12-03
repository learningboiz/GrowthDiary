import {useNavigate} from "react-router-dom";
import {useContext} from "react";
import {SessionContext} from "../SessionContext.jsx";

export default function StartNewSession() {

    const navigate = useNavigate();
    const {resetFormValues} = useContext(SessionContext);

    const startNewSession = (e) => {
        e.preventDefault();
        resetFormValues();
        navigate('/session/new', { replace: true});
    }

    return <button onClick={startNewSession}>Start a new session</button>
}