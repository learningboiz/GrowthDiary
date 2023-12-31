import {useEffect, useState} from "react";
import weeklySummaryAPI from "./api/weeklySummaryAPI.js";
import {splitPeriodIntoDateTime} from "../../utility/splitPeriodIntoDateTime.js";
import WeeklySummaryTable from "./WeeklySummaryTable.jsx";

export default function AnalyticsView() {

    const [weeklySummary, setWeeklySummary] = useState({});

    useEffect(() => {

        const usersCurrentPeriod = new Date().toISOString();
        const usersCurrentDate = splitPeriodIntoDateTime(usersCurrentPeriod)[0];

        const fetchWeeklySummary = async () => {
            const data = await weeklySummaryAPI(usersCurrentDate);
            const json = await data.json();

            console.log(json);
            setWeeklySummary(json);
        }

        fetchWeeklySummary()
            .catch(console.error);
    }, []);




    return (
        <>
            <h2>Welcome to the analytics page</h2>
            <WeeklySummaryTable weeklySummary={weeklySummary}/>
        </>
    )
}