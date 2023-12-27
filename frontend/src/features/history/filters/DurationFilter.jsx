import VerticalRadioGroup from "../../../components/inputfields/VerticalRadioGroup.jsx";
import {duration} from "@mui/material";
export default function DurationFilter({setFilterRequest}) {

    const handleSaveDurationFilter = (e) => {

        {/* Apparently value only takes in a string
            Unable to assign value an object
            Hence using this approach */}

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
        {value: "underOneHour", name: "durationFilter", text: "Under 1 hour"},
        {value: "oneToTwoHours", name: "durationFilter", text: "Between 1 to 2 hours"},
        {value: "overTwoHours", name: "durationFilter", text: "More than 2 hours"}
    ]

    return (
            <>
                <h2 className="text-sm mb-3 font-medium text-indigo-500 border-gray-300 border-b pb-1">Duration</h2>
                <VerticalRadioGroup
                    handleOnClick={handleSaveDurationFilter}
                    optionsArray={durationFilterOptions}/>
            </>
        )


}