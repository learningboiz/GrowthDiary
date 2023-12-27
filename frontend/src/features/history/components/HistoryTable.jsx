import HistoryTableRow from "./HistoryTableRow.jsx";

export default function HistoryTable({sessionArray}) {
    return (
        <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-indigo-400">
            <tr>

                <th scope="col" className="px-6 py-3 text-start text-xs font-medium uppercase">
                    <div className="flex items-center gap-x-2">
                        <span className="text-xs font-semibold uppercase tracking-wide text-neutral-50">
                            Topic
                        </span>
                    </div>
                </th>

                <th scope="col" className="px-6 py-3 text-start text-xs font-medium uppercase">
                    <div className="flex items-center gap-x-2">
                        <span className="text-xs font-semibold uppercase tracking-wide text-neutral-50">
                            Description
                        </span>
                    </div>
                </th>

                <th scope="col" className="px-6 py-3 text-start text-xs font-medium uppercase">
                    <div className="flex items-center gap-x-2">
                        <span className="text-xs font-semibold uppercase tracking-wide text-neutral-50">
                            Date
                        </span>
                    </div>
                </th>

                <th scope="col" className="px-6 py-3 text-start text-xs font-medium uppercase">
                    <div className="flex items-center gap-x-2">
                        <span className="text-xs font-semibold uppercase tracking-wide text-neutral-50">
                            Time
                        </span>
                    </div>
                </th>

                <th scope="col" className="px-6 py-3 text-start text-xs font-medium uppercase">
                    <div className="flex items-center gap-x-2">
                        <span className="text-xs font-semibold uppercase tracking-wide text-neutral-50">
                            Duration
                        </span>
                    </div>
                </th>

                <th scope="col" className="px-6 py-3 text-start text-xs font-medium uppercase">
                    <div className="flex items-center gap-x-2">
                        <span className="text-xs font-semibold uppercase tracking-wide text-neutral-50">
                            Obstacle
                        </span>
                    </div>
                </th>

                <th scope="col" className="px-6 py-3 text-start text-xs font-medium uppercase">
                    <div className="flex items-center gap-x-2">
                        <span className="text-xs font-semibold uppercase tracking-wide text-neutral-50">
                            Productivity
                        </span>
                    </div>
                </th>

            </tr>
            </thead>

            <tbody className="divide-y divide-gray-200">
            {sessionArray && sessionArray.map((session, sessionID) => (
                <HistoryTableRow key={sessionID} session={session} />
            ))}
            </tbody>
        </table>
    )
}
