import AnalyticsPage from "../analytics/AnalyticsPage.jsx";

export default function NavigateToAnalytics() {

    function handleOnClick() {
        return <AnalyticsPage />
    }

    return <button onClick={handleOnClick}>Analytics</button>
}