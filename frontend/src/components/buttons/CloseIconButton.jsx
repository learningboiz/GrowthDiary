export default function CloseIconButton({handleOnClick}) { // icons from https://heroicons.com/
    return (
        <>
            <button
                type="button"
                onClick={handleOnClick}
                className="flex flex-shrink-0 justify-center items-center gap-2 h-[2.375rem] w-[2.375rem] text-sm font-semibold rounded-lg border border-transparent bg-indigo-500 text-white hover:bg-indigo-600">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M6 18 18 6M6 6l12 12" />
                </svg>
            </button>
        </>
    )
}