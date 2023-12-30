export default function SingleSelect({handleOnChange, optionList, label, placeholderText}) {
    return (
        <>
            <select
                onChange={handleOnChange}
                className="py-2 px-3 block border border-indigo-500 rounded-md text-sm text-gray-600 focus:border-indigo-500 focus:ring-blue-500">
                <option value="" disabled>{placeholderText}</option>
                {optionList && optionList.map((option, optionId) => (
                    <option key={optionId} value={option.value}>{option.text}</option>
                ))}
            </select>
        </>
    )

}