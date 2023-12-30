export default function PageSizeToggle({setHistoryDTO}) {
    
    const handlePageSizeToggle = (e) => {
        e.preventDefault();

        setHistoryDTO((prev) => ({
            ...prev,
            pageViewRequest: {
                ...prev.pageViewRequest,
                pageSize: e.target.value
            }
        }));
    }

    return (
        <div className="inline-flex items-center gap-x-2">
            <p className="text-sm text-indigo-500">
                Items per page
            </p>
            <div className="max-w-sm space-y-3">
                <select className="py-2 px-3 pe-2 block w-full bg-indigo-500 border-gray-200 rounded-lg text-sm font-medium text-neutral-50 focus:border-blue-500 focus:ring-blue-500"
                        onChange={handlePageSizeToggle}>
                    <option value={10}>10</option>
                    <option value={15}>15</option>
                    <option value={20}>20</option>
                </select>
            </div>
        </div>
    )
}

