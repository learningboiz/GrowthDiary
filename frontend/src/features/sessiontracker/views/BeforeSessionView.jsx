import OutlineButton from "../../../components/buttons/OutlineButton.jsx";
import {useNavigate} from "react-router-dom";
import {useContext} from "react";
import {SessionContext} from "../SessionContext.jsx";

export default function BeforeSessionView() {

    const navigate = useNavigate();
    const {resetFormValues} = useContext(SessionContext);

    const startNewSession = (e) => {
        e.preventDefault();
        resetFormValues();
        navigate("/session/new", {replace: true})
    }

    const startOldSession = (e) => {
        e.preventDefault();
        resetFormValues();
        navigate("/session/old", {replace: true})
    }



    return (
        <div className="py-8 px-4 mx-auto max-w-2xl lg:py-16">
            <h2 className="text-2xl font-bold mb-4 text-indigo-600">Track your learning session</h2>
            <div className="flex flex-col gap-4">
                <OutlineButton handleOnClick={startNewSession} buttonText={"Start new"} />
                <OutlineButton handleOnClick={startOldSession} buttonText={"Record previous"} />
            </div>
        </div>
    )
}
