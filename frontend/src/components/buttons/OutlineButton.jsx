export default function OnlineButton({handleOnClick, buttonText}) {
    return <button
        className="py-2 px-3 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-indigo-500 text-indigo-500 hover:bg-indigo-500 hover:text-neutral-50 disabled:opacity-50 disabled:pointer-events-none"
        onClick={handleOnClick}>
        {buttonText}</button>
}