export default function CheckboxInput({inputArrayState, handleOnChange, optionsArray}) {
    return (
        <>
            {optionsArray && // null check; prevents render if optionsArray is null
                optionsArray.map((option, optionId) => (
                <div key={optionId} className="flex">
                    <input type="checkbox"
                           value={option}
                           onChange={handleOnChange}
                           checked={inputArrayState.includes(option)} // because multi-check options are key within array
                           className="shrink-0 mt-0.5 border-gray-200 rounded text-indigo-500 focus:ring-indigo-500" />
                    <label className="text-sm text-gray-500 ms-3">{option}</label>
                </div>
            ))}
        </>
    )
}