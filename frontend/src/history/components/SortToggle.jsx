export default function SortToggle({setHistoryDTO}) {

    const handleSortToggle = (e) => {
        e.preventDefault();

        const sortValues = e.target.value.split(',');

        const sortRequest = {
            property: sortValues[0],
            sortDirection: sortValues[1]
        }
        setHistoryDTO((prev) => ({
            ...prev,
            sortRequest: sortRequest
        }));
        console.log("property is" + sortValues[0])
        console.log("direction is" + sortValues[1])
    }

    return (
        <select onChange={handleSortToggle}>
            <option value={"details.topic,ASC"}>Topic</option>
            <option value={"time.duration,ASC"}>Duration</option>
            <option value={"time.startDate,ASC"}>Date</option>
            <option value={"feedback.productivity,ASC"}>Productivity</option>
        </select>
    )
}