import BasicInput from "../../../components/inputfields/BasicInput.jsx";
export default function DescriptionFilter() {
    return (
        <>
            <label className="block text-sm font-medium mb-2">Enter a keyword or keyphrase</label>
            <BasicInput inputType={"text"} placeHolder={"e.g Backend API"} />
        </>
    )
}