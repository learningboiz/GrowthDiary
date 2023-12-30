import {useEffect, useState} from "react";

export default function PaginationToggle({totalPages, setHistoryDTO}) {

    // REMINDER: the pagination json is 0-index meaning that page numbers start from 0
    // The total pages is accurate (i.e if 11 pages, it means 11 total not 12)
    // Setting component state to 1 for readability
    // When setting state for API call, remember to subtract 1 from component state

    const [pageUserIsOn, setPageUserIsOn] = useState(1);

    useEffect(() => {

        let pageReset;

        if (totalPages > 0) {
            pageReset = 1;
        } else {
            pageReset = 0
        }

        setPageUserIsOn(pageReset); // set display back to first page

        setHistoryDTO((prev) => ({ // request for the first page
            ...prev,
            pageViewRequest: {
                ...prev.pageViewRequest,
                pageIndex: 0
            }
        }));
    }, [setHistoryDTO, totalPages]);

    const handlePrevPage = () => {

        let pageUserSelected = pageUserIsOn;

        if (pageUserSelected > 1) {
            pageUserSelected = pageUserSelected - 1; // go back by one
            setPageUserIsOn(pageUserSelected);
        }

        setHistoryDTO((prev) => ({
            ...prev,
            pageViewRequest: {
                ...prev.pageViewRequest,
                pageIndex: pageUserSelected - 1 // subtract 1 since server pagination is 0-indexed
            }
        }));
    }

    const handleNextPage = () => {

        let pageUserSelected = pageUserIsOn;

        if (pageUserSelected < totalPages) {
            pageUserSelected = pageUserSelected + 1 // move up by one
            setPageUserIsOn(pageUserSelected);
        }

        setHistoryDTO((prev) => ({
            ...prev,
            pageViewRequest: {
                ...prev.pageViewRequest,
                pageIndex: pageUserSelected - 1 // subtract 1 since server pagination is 0-indexed
            }
        }));
    }


    return (
        <nav className="flex items-center gap-x-1">
            <button
                type="button"
                onClick={handlePrevPage}
                className="min-h-[38px] min-w-[38px] py-2 px-2.5 inline-flex justify-center items-center gap-x-2 text-sm rounded-lg text-gray-800 hover:bg-gray-100 focus:outline-none focus:bg-gray-100">
                <svg className="flex-shrink-0 w-3.5 h-3.5" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="m15 18-6-6 6-6"/></svg>
                <span aria-hidden="true" className="sr-only">Previous</span>
            </button>
            <div className="flex items-center gap-x-1">
                <span className="min-h-[38px] min-w-[38px] flex justify-center items-center border border-indigo-300 text-indigo-600 py-2 px-3 text-sm rounded-lg focus:outline-none focus:bg-gray-50">{pageUserIsOn}</span>
                <span className="min-h-[38px] flex justify-center items-center text-gray-500 py-2 px-1.5 text-sm">of</span>
                <span className="min-h-[38px] flex justify-center items-center text-gray-500 py-2 px-1.5 text-sm">{totalPages}</span>
            </div>
            <button
                type="button"
                onClick={handleNextPage}
                className="min-h-[38px] min-w-[38px] py-2 px-2.5 inline-flex justify-center items-center gap-x-2 text-sm rounded-lg text-gray-800 hover:bg-gray-100 focus:outline-none focus:bg-gray-100">
                <span aria-hidden="true" className="sr-only">Next</span>
                <svg className="flex-shrink-0 w-3.5 h-3.5" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="m9 18 6-6-6-6"/></svg>
            </button>
        </nav>
    )
}