export default function PageViewToggle({setHistoryDTO}) {
    
    const handlePageSizeToggle = (e) => {
        e.preventDefault();

        const pageViewRequest = {
            pageIndex: null,
            pageSize: e.target.value
        }
        setHistoryDTO((prev) => ({
            ...prev,
            pageViewRequest: pageViewRequest
        }));
    }

    return (
        <div className="inline-flex items-center gap-x-2">
            <p className="text-sm text-gray-600">
                Items per page:
            </p>
            <div className="max-w-sm space-y-3">
                <select className="py-2 px-3 pe-9 block w-full bg-indigo-500 border-gray-200 rounded-lg text-sm font-medium text-neutral-50 focus:border-blue-500 focus:ring-blue-500"
                        onChange={handlePageSizeToggle}>
                    <option value={10}>10</option>
                    <option value={15}>15</option>
                    <option value={20}>20</option>
                </select>
            </div>
        </div>
    )
}

