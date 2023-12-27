export default function VerticalRadioGroup({handleOnClick, optionsArray}) {
    return (
        <>
            {optionsArray && optionsArray.map((option, optionId) => (
                <div key={optionId} className="flex">
                    <input type="radio"
                           name={option.name}
                           value={option.value}
                           onClick={handleOnClick}
                           className="shrink-0 mt-0.5 border-gray-200 rounded-full text-indigo-500 focus:ring-indigo-500" />
                    <label className="text-sm text-gray-500 ms-2">{option.text}</label>
                </div>
            ))}
        </>
    )
}