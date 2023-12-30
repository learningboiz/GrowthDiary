export default function IconButtonWithText({iconSvg, buttonText, handleOnClick}) {
    return (
        <>
            <button type="button"
                    onClick={handleOnClick}
                    className="py-1 px-3 inline-flex items-center gap-x-2 text-sm font-medium rounded-md border border-transparent bg-indigo-500 text-white hover:bg-indigo-600">
                {buttonText}
                {iconSvg}
            </button>
        </>
    )
}