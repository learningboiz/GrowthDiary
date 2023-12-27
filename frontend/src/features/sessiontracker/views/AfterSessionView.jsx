import {useNavigate} from "react-router-dom";

export default function AfterSessionView({header, message}) {

    const navigate = useNavigate();

    const handleSessionReturn = (e) => {
        e.preventDefault();
        navigate('/session');
    }

    const handleHistoryReturn = (e) => {
        e.preventDefault();
        navigate('/history');
    }

    return (
        <div className="py-8 px-4 mx-auto max-w-2xl lg:py-16">
            <h2 className="text-2xl font-bold mb-4 text-indigo-600">{header}</h2>
            <h3 className="text-base -mt-2 mb-6 text-gray-600 border-gray-300 border-b pb-3">{message}</h3>
            <div className="flex flex-col gap-4">
                <button onClick={handleSessionReturn}
                        className="py-2 px-3 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-indigo-500 text-indigo-500 hover:bg-indigo-500 hover:text-neutral-50 disabled:opacity-50 disabled:pointer-events-none">
                    Start another session
                </button>
                <button onClick={handleHistoryReturn}
                        className="py-2 px-3 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-indigo-500 text-indigo-500 hover:bg-indigo-500 hover:text-neutral-50 disabled:opacity-50 disabled:pointer-events-none">
                    Check session history
                </button>
            </div>
        </div>
    )
}