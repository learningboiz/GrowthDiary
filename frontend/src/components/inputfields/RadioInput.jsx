export default function RadioInput({inputState, handleOnChange, optionsArray}) {

    return (
        <>
            {optionsArray &&  // null check; prevents render if optionsArray is null
                optionsArray.map((option, optionId) => (
                <div key={optionId} className="flex">
                    <input type="radio"
                           name={option.name}
                           value={option.value}
                           onChange={handleOnChange}
                           checked={inputState === option.value}
                           className="shrink-0 mt-0.5 border-gray-200 rounded-full text-indigo-500 focus:ring-indigo-500" />
                    <label className="text-sm text-gray-500 ms-2">{option.label}</label>
                </div>
            ))}
        </>
    )
}