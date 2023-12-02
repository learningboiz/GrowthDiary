import {useNavigate} from "react-router-dom";

export default function SessionStartButton() {

    const navigate = useNavigate();

    const startNewSession = (e) => {
        e.preventDefault();
        navigate('/session/new', { replace: true});
    }

    return <button onClick={startNewSession}>Start a new session</button>
}