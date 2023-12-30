import SingleSelect from "../../../components/inputfields/SingleSelect.jsx";
export default function SortToggle({setHistoryDTO}) {

    const sortList = [
        {value: "details.topic,ASC", text: "Topic"},
        {value: "time.startDate,ASC", text: "Earliest date"},
        {value: "time.startDate,DESC", text: "Latest date"},
        {value: "time.duration,DESC", text: "Longest duration"},
        {value: "feedback.productivity,DESC", text: "Highest productivity"}
    ]

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
        <div className="flex-row items-center font-medium gap-x-2">
            <SingleSelect
                handleOnChange={handleSortToggle}
                optionList={sortList}
                placeholderText={"Sort by"}
            />
        </div>
    )
}