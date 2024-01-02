import {useEffect, useState} from "react";
import HistoryTable from "./components/HistoryTable.jsx";
import defaultHistoryAPI from "./api/defaultHistoryAPI.js";
import customHistoryAPI from "./api/customHistoryAPI.js";
import PageSizeToggle from "./components/PageSizeToggle.jsx";
import SortToggle from "./components/SortToggle.jsx";
import FilterToggle from "./components/FilterToggle.jsx";
import PaginationToggle from "./components/PaginationToggle.jsx";

export default function HistoryView() {

    const [historyDataAvailable, setHistoryDataAvailable] = useState(false);

    const [sessionArray, setSessionArray] = useState();
    const [historyDTO, setHistoryDTO] = useState({});

    const [totalPages, setTotalPages] = useState(0);

    useEffect(() => {

        const fetchDefaultHistory = async () => {
            const data = await defaultHistoryAPI();
            const json = await data.json();

            if (json.content.length !== 0) {
                setHistoryDataAvailable(true);
                setSessionArray(json.content)
                setTotalPages(json.totalPages);
            }
        }
        fetchDefaultHistory()
            .catch(console.error);
    }, []);

    useEffect(() => {

        const fetchCustomHistory = async () => {
            const data = await customHistoryAPI(historyDTO);
            const json = await data.json();

            setSessionArray(json.content)
            setTotalPages(json.totalPages);
        }
        fetchCustomHistory()
            .catch(console.error);
    }, [historyDTO]);

    return (
        <div>
            <h2
                className="text-lg font-bold mb-4 text-indigo-600 pl-2 text-center
                sm:text-2xl sm:text-left"
            >Review your session history</h2>

            {historyDataAvailable &&
                <>
                    {/* Toggles */}
                    <div
                        className="flex flex-col gap-4 pl-2 pb-4 items-center justify-center
                sm:flex-row sm:items-start sm:justify-start">
                        <SortToggle setHistoryDTO={setHistoryDTO} />
                        <FilterToggle setHistoryDTO={setHistoryDTO} />
                    </div>

                    {/* Main header */}

                    <div className="-m-1.5 overflow-x-auto">
                        <div className="p-1.5 min-w-full inline-block align-middle">
                            <div className="overflow-hidden">
                                <HistoryTable sessionArray={sessionArray} />
                            </div>
                        </div>
                    </div>

                    {/* Footer */}

                    <div className="px-4 py-4 gap-3 flex flex-col items-center justify-center
            sm:flex sm:flex-row sm:justify-between sm:items-center border-t border-gray-200">
                        <PageSizeToggle setHistoryDTO={setHistoryDTO}/>
                        <PaginationToggle totalPages={totalPages} setHistoryDTO={setHistoryDTO} />
                    </div>
                </>
            }
            {!historyDataAvailable &&
                <p className="text-sm font-light mb-4 text-indigo-600 pl-2 text-center
                sm:text-base sm:text-left">There is currently no session data available</p>}
        </div>
    )
}