import RadioInput from "../../../components/inputfields/RadioInput.jsx";
import getDateDaysAgo from "../utility/getDateDaysAgo.js";
import {splitPeriodIntoDateTime} from "../../../utility/splitPeriodIntoDateTime.js";
export default function DateFilter({setFilterRequest, dateInput, setDateInput}) {

    const handleOnChange = (e) => {

        const selectedOption = parseInt(e.target.value, 10);
        setDateInput(selectedOption);

        let dateFilter;

        const daysAgo = selectedOption;
        const dateToday = new Date();
        const dateDaysAgo = getDateDaysAgo(dateToday, daysAgo);

        const dateRangeStart = splitPeriodIntoDateTime(dateDaysAgo.toISOString())[0];
        const dateRangeEnd = splitPeriodIntoDateTime(dateToday.toISOString())[0];

        if (daysAgo === 0) {
            dateFilter = {
                dateOperation: "EQUALS",
                primaryDate: dateRangeEnd,
            }
        } else {
            dateFilter = {
                dateOperation: "BETWEEN",
                primaryDate: dateRangeStart,
                secondaryDate: dateRangeEnd
            }
        }

        setFilterRequest((prev) => ({
            ...prev,
            timeFilter: {
                ...prev.timeFilter,
                ...dateFilter
            }
        }));

    }

    const dateFilterOptions = [
        {value: 0, name: "dateFilter", label: "Today"},
        {value: 7, name: "dateFilter", label: "Past 7 days"},
        {value: 30, name: "dateFilter", label: "Past 30 days"},
        {value: 90, name: "dateFilter", label: "Past 90 days"}
    ]

    return (
        <>
            <h2 className="text-sm mb-3 font-medium text-indigo-500 border-gray-300 border-b pb-1">Date</h2>
            <RadioInput
                inputState={dateInput}
                handleOnChange={handleOnChange}
                optionsArray={dateFilterOptions}/>
        </>
    )
}