export default function SingleSelect({handleOnChange, optionList}) {
    return <select
        onChange={handleOnChange}
        className="py-3 px-5 block w-full border border-gray-500 rounded-lg text-sm text-gray-600 focus:border-indigo-500 focus:ring-blue-500 disabled:opacity-50">
        {optionList && optionList.map((option, optionId) => (
            <option key={optionId} value={option.value}>{option.text}</option>
        ))}
    </select>

}