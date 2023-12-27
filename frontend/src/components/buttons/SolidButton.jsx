export default function SolidButton({handleOnClick, buttonText}) {
    return (
        <button
            onClick={handleOnClick}
            type="submit"
            className="py-2 px-3 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-indigo-500 text-white hover:bg-indigo-600">
            {buttonText}
        </button>
    )
}