import TextInput from "../../../components/inputfields/TextInput.jsx";

export default function TopicFilter({setFilterRequest, topicInput, setTopicInput}) {

    const handleOnChange = (e) => {
        e.preventDefault();
        const topicString = e.target.value;

        setTopicInput(topicString);

        const topicArray = topicString.split(",");

        setFilterRequest((prev) => ({
            ...prev,
            detailsFilter: {
                ...prev.detailsFilter,
                topics: topicArray
            }
        }));
    }

    return (
        <>
            <h2 className="text-sm mb-3 font-medium text-indigo-500 border-gray-300 border-b pb-1">Topic</h2>
            <TextInput
                handleOnChange={handleOnChange}
                inputState={topicInput}
                placeHolder={"Separate each topic with a comma"} />
        </>
    )
}