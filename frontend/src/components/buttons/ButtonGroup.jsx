export default function ButtonGroup({handleOnClick, filterTypeArray}) {
    return (
        <div className="inline-flex rounded-lg shadow-sm">
            {filterTypeArray && filterTypeArray.map((filterType, filterTypeId) => (
                <button type="button"
                        key={filterTypeId}
                        onClick={() => handleOnClick(filterType)}
                        className="py-3 px-4 inline-flex items-center gap-x-2 -ms-px first:rounded-s-lg first:ms-0 last:rounded-e-lg text-sm font-medium focus:z-10 border border-gray-200 bg-white text-gray-800 shadow-sm hover:bg-gray-50 disabled:opacity-50 disabled:pointer-events-none">
                    {filterType}
                </button>
            ))}
        </div>
    )
}