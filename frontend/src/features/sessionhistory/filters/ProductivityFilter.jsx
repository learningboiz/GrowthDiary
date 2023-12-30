import RadioInput from "../../../components/inputfields/RadioInput.jsx";
export default function ProductivityFilter({setFilterRequest, productivityInput, setProductivityInput}) {

    const handleOnChange = (e) => {

        const selectedOption = e.target.value;
        setProductivityInput(selectedOption);

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
        {value: "moderateRange", name: "productivityFilter", label: "Moderate"},
        {value: "highRange", name: "productivityFilter", label: "High - Very High"},
        {value: "lowRange", name: "productivityFilter", label: "Low - Very Low"}
    ]

    return (
        <>
            <h2 className="text-sm mb-3 font-medium text-indigo-500 border-gray-300 border-b pb-1">Productivity</h2>
            <RadioInput
                inputState={productivityInput}
                handleOnChange={handleOnChange}
                optionsArray={productivityFilterOptions}/>
        </>
    )
}