import RadioInput from "../../../components/inputfields/RadioInput.jsx";
export default function DurationFilter({setFilterRequest, durationInput, setDurationInput}) {

    const handleOnChange = (e) => {

        const selectedOption = e.target.value;
        setDurationInput(selectedOption);

        let durationFilter;

        const selectedValue = e.target.value;

        if (selectedValue === "underOneHour") {
            durationFilter = {
                durationOperation: "LESS_THAN",
                primaryDuration: 60
            }
        } else if (selectedValue === "oneToTwoHours") {
            durationFilter = {
                durationOperation: "BETWEEN",
                primaryDuration: 60,
                secondaryDuration: 120
            }
        } else if (selectedValue === "overTwoHours") {
            durationFilter = {
                durationOperation: "GREATER_THAN",
                primaryDuration: 120
            }
        }

        setFilterRequest((prev) => ({
            ...prev,
            timeFilter: {
                ...prev.timeFilter,
                ...durationFilter
            }
        }));
    }


    const durationFilterOptions = [
        {value: "underOneHour", name: "durationFilter", label: "Under 1 hour"},
        {value: "oneToTwoHours", name: "durationFilter", label: "Between 1 to 2 hours"},
        {value: "overTwoHours", name: "durationFilter", label: "More than 2 hours"}
    ]

    return (
            <>
                <h2 className="text-sm mb-3 font-medium text-indigo-500 border-gray-300 border-b pb-1">Duration</h2>
                <RadioInput
                    inputState={durationInput}
                    handleOnChange={handleOnChange}
                    optionsArray={durationFilterOptions}/>
            </>
        )


}