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
        <select onChange={handlePageSizeToggle}>
            <option value={10}>10 per page</option>
            <option value={20}>20 per page</option>
            <option value={30}>30 per page</option>
        </select>
    )
}