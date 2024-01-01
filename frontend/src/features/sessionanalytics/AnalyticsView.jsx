import {useEffect, useState} from "react";
import sessionStatsAPI from "./api/sessionStatsAPI.js";
import {splitPeriodIntoDateTime} from "../../utility/splitPeriodIntoDateTime.js";
import WeeklySummaryTable from "./components/WeeklySummaryTable.jsx";
import ProductivityChart from "./components/ProductivityChart.jsx"
import productivityChartAPI from "./api/productivityChartAPI.js";
import SingleSelect from "../../components/inputfields/SingleSelect.jsx";
import {chartCategories} from "./chartCategories.js";

export default function AnalyticsView() {

    const [weeklySummary, setWeeklySummary] = useState({});
    const [summaryDataAvailable, setSummaryDataAvailable] = useState(false);


    const [chartData, setChartData] = useState({});
    const [chartDataAvailable, setChartDataAvailable] = useState(false);
    const [chartCategory, setChartCategory] = useState("obstacle");

    useEffect(() => {

        const fetchWeeklySummary = async () => {
            const data = await sessionStatsAPI();
            const json = await data.json();

            if (isValidSummaryData(json)) {
                setWeeklySummary(json);
                setSummaryDataAvailable(true);
            }

            console.log(json);

        }

        fetchWeeklySummary()
            .catch(console.error);
    }, []);

    useEffect(() => {

        const fetchProductivityChart = async () => {
            const data = await productivityChartAPI(chartCategory);
            const json = await data.json();

            console.log(json);
            console.log(json.length);
            console.log(chartCategory);

            if (isValidChartData(json)) {

                setChartData(json);
                setChartDataAvailable(true);
            }
        }

        fetchProductivityChart()
            .catch(console.error);
    }, [chartCategory])

    const isValidSummaryData = (json) => {
        return json.topTopic != null
            && json.topObstacle != null
            && json.totalDuration != null
            && json.avgDuration != null
            && json.avgProductivity != null
    }

    const isValidChartData = (json) => {
        return json.length !== 0;
    }

    const handleChartCategory = (e) => {
        setChartCategory(e.target.value);
    }


    return (
        <>
            <h2 className="text-lg font-bold mb-4 text-indigo-600 pl-4 text-center
                sm:text-2xl sm:text-left"
            >Analyse your session stats</h2>

            {summaryDataAvailable &&
                <div>
                    <p className="text-sm font-light mb-4 text-indigo-600 pl-4 text-center
                sm:text-base sm:text-left">Total Session Stats</p>
                    <WeeklySummaryTable weeklySummary={weeklySummary}/>
                </div>}

            {chartDataAvailable &&
                <div className="pt-8 flex flex-col items-center sm:items-start">
                    <div className="flex flex-col items-center">
                        <p className="text-sm font-light mb-4 text-indigo-600 pl-4 text-center
                sm:text-base sm:text-left">Productivity Chart</p>
                        <div className="-m-2 pl-6">
                            <SingleSelect handleOnChange={handleChartCategory}
                                          optionList={chartCategories}
                                          placeholderText={"Pick a category"} />
                        </div>
                    </div>
                    <ProductivityChart chartValues={chartData} category={chartCategory}/>

                </div>
            }

            {(!summaryDataAvailable || !chartDataAvailable) &&
                <p className="text-sm font-light mb-4 text-indigo-600 pl-2 text-center
                sm:text-base sm:text-left">There is currently no session data available</p>}

        </>
    )
}