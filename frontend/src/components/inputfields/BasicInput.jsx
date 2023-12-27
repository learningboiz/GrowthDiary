export default function BasicInput({inputType, placeHolder, handleOnChange}) {
    return <input type={inputType}
                  placeholder={placeHolder}
                  onChange={handleOnChange}
                  className="py-3 px-5 block w-full border border-gray-500 rounded-lg text-sm text-gray-500 focus:border-indigo-500 focus:ring-blue-500 disabled:opacity-50"
            />
}