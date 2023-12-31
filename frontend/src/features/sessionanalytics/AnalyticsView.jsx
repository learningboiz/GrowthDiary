import {useEffect, useState} from "react";
import weeklySummaryAPI from "./api/weeklySummaryAPI.js";
import {splitPeriodIntoDateTime} from "../../utility/splitPeriodIntoDateTime.js";
import WeeklySummaryTable from "./WeeklySummaryTable.jsx";

export default function AnalyticsView() {

    const [weeklySummary, setWeeklySummary] = useState({});
    const [summaryDataAvailable, setSummaryDataAvailable] = useState(false);

    useEffect(() => {

        const usersCurrentPeriod = new Date().toISOString();
        const usersCurrentDate = splitPeriodIntoDateTime(usersCurrentPeriod)[0];

        const fetchWeeklySummary = async () => {
            const data = await weeklySummaryAPI(usersCurrentDate);
            const json = await data.json();

            if (isCompleteData(json)) {
                setWeeklySummary(json);
                setSummaryDataAvailable(true);
            }

            console.log(json);

        }

        fetchWeeklySummary()
            .catch(console.error);
    }, []);

    const isCompleteData = (json) => {
        return json.topTopic != null && json.totalDuration != null && json.avgProductivity != null
    }




    return (
        <>
            <h2 className="text-lg font-bold mb-4 text-indigo-600 pl-2 text-center
                sm:text-2xl sm:text-left"
            >Analyse your session stats</h2>
            {summaryDataAvailable && <WeeklySummaryTable weeklySummary={weeklySummary}/>}
            {!summaryDataAvailable &&
                <p className="text-sm font-light mb-4 text-indigo-600 pl-2 text-center
                sm:text-base sm:text-left">There is currently no session data available</p>}
        </>
    )
}