import HistoryPage from "../history/HistoryPage.jsx";

export default function NavigateToHistory() {

    function handleOnClick() {
        return <HistoryPage />
    }

    return <button onClick={handleOnClick}>History</button>
}