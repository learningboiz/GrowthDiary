import TextInput from "../../../components/inputfields/TextInput.jsx";
export default function DescriptionFilter({setFilterRequest, descriptionInput, setDescriptionInput}) {

    const handleOnChange = (e) => {
        e.preventDefault();

        const givenDescription = e.target.value;
        setDescriptionInput(givenDescription);

        setFilterRequest((prev) => ({
            ...prev,
            detailsFilter: {
                ...prev.detailsFilter,
                description: givenDescription
            }
        }));
    }

    return (
        <>
            <h2 className="text-sm mb-3 font-medium text-indigo-500 border-gray-300 border-b pb-1">Description</h2>
            <TextInput
                handleOnChange={handleOnChange}
                inputState={descriptionInput}
                placeHolder={"Enter a keyword or keyphrase"} />
        </>
    )
}