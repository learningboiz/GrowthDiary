import {useNavigate} from "react-router-dom";
import {useState} from "react";

export default function SessionFormLayout() {
    const [formStarted, setFormStarted] = useState(false)
    const navigate = useNavigate()

    const handleClick = (e) => {
        e.preventDefault();
        setFormStarted(true);
        navigate("details")
    }

    return (
        <>
            <h2>Track your learning session</h2>
            {!formStarted && <button onClick={handleClick}>Begin</button>}
        </>
    )
}