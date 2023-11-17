import MainSessionPage from "../tracker/pages/MainSessionPage.jsx";

export default function NavigateToSession() {

    function handleOnClick() {
        return <MainSessionPage />
    }

    return (
        <button onClick={handleOnClick}>Session</button>
    )
}