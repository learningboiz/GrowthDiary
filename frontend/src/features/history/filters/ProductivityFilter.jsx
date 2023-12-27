import VerticalRadioGroup from "../../../components/inputfields/VerticalRadioGroup.jsx";
export default function ProductivityFilter({setFilterRequest}) {

    const handleSaveProductivityFilter = (e) => {

        {/* Apparently value only takes in a string
            Unable to assign value an object
            Hence using this approach */}

        let productivityFilter;

        const selectedValue = e.target.value;

        if (selectedValue === "moderateRange") {
            productivityFilter = {
                productivityOperation: "EQUALS",
                primaryProductivity: 3
            }
        } else if (selectedValue === "highRange") {
            productivityFilter = {
                productivityOperation: "GREATER_THAN",
                primaryProductivity: 3
            }
        } else if (selectedValue === "lowRange") {
            productivityFilter = {
                productivityOperation: "LESS_THAN",
                primaryProductivity: 3
            }
        }

        setFilterRequest((prev) => ({
            ...prev,
            feedbackFilter: {
                ...prev.feedbackFilter,
                ...productivityFilter
            }
        }));
    }


    const productivityFilterOptions = [
        {value: "moderateRange", name: "productivityFilter", text: "Moderate"},
        {value: "highRange", name: "productivityFilter", text: "High - Very High"},
        {value: "lowRange", name: "productivityFilter", text: "Low - Very Low"}
    ]

    return (
        <>
            <h2 className="text-sm mb-3 font-medium text-indigo-500 border-gray-300 border-b pb-1">Productivity</h2>
            <VerticalRadioGroup
                handleOnClick={handleSaveProductivityFilter}
                optionsArray={productivityFilterOptions}/>
        </>
    )
}