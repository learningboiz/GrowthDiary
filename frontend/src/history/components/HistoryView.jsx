import {useEffect, useState} from "react";
import HistoryTable from "./HistoryTable.jsx";
import defaultHistoryAPI from "../../api/defaultHistoryAPI.js";
import customHistoryAPI from "../../api/customHistoryAPI.js";
import PageViewToggle from "./PageViewToggle.jsx";
import SortToggle from "./SortToggle.jsx";
import FilterPopup from "../filtercomponents/FilterModal.jsx";
import HistoryTableFooter from "../utility/HistoryTableFooter.jsx";
import PaginationToggle from "./PaginationToggle.jsx";

export default function HistoryView() {

    const [sessionArray, setSessionArray] = useState();
    const [historyDTO, setHistoryDTO] = useState({});
    const [filterModalOpen, setFilterModalOpen] = useState(false);

    useEffect(() => {

        const fetchDefaultHistory = async () => {
            const data = await defaultHistoryAPI();
            const json = await data.json();

            setSessionArray(json.content)
            console.log(json);
        }
        fetchDefaultHistory()
            .catch(console.error);
    }, []);

    useEffect(() => {

        const fetchCustomHistory = async () => {
            const data = await customHistoryAPI(historyDTO);
            const json = await data.json();

            console.log(json);
            console.log(historyDTO);
            setSessionArray(json.content)
        }
        fetchCustomHistory()
            .catch(console.error);
    }, [historyDTO]);

    const toggleFilterModal = () => {
        setFilterModalOpen(true);
    }

    return (
        <div>
            <h2>Review your session history</h2>

            {/* Toggles */}
            <div className="flex-row">
                <SortToggle setHistoryDTO={setHistoryDTO} />
                <button onClick={toggleFilterModal}>Filter</button>
                <div>
                    {filterModalOpen && <FilterPopup />}
                </div>
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

            <div className="px-6 py-4 grid gap-3 md:flex md:justify-between md:items-center border-t border-gray-200 dark:border-gray-700">
                <PageViewToggle setHistoryDTO={setHistoryDTO}/>
                <PaginationToggle />
            </div>


        </div>
    )
}