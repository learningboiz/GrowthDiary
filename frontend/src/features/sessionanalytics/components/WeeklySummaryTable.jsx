import {splitDurationIntoHoursMinutes} from "../../../utility/splitDurationIntoHoursMinutes.js";

export default function WeeklySummaryTable({weeklySummary}) {

    const [totalHours, totalMinutes] = splitDurationIntoHoursMinutes(weeklySummary.totalDuration);
    const [avgHours, avgMinutes] = splitDurationIntoHoursMinutes(weeklySummary.avgDuration);

    return (
        <div className="flex flex-col">
            <div className="-m-1.5 overflow-x-auto">
                <div className="p-1.5 min-w-full inline-block align-middle">
                    <div className="border rounded-lg shadow overflow-hidden">
                        <table className="min-w-full divide-y divide-gray-200">
                            <thead className="bg-indigo-600">
                            <tr>
                                <th scope="col" className="px-6 py-3 text-start text-xs font-medium text-neutral-50 uppercase">Most worked on topic</th>
                                <th scope="col" className="px-6 py-3 text-start text-xs font-medium text-neutral-50 uppercase">Most frequent obstacle</th>
                                <th scope="col" className="px-6 py-3 text-start text-xs font-medium text-neutral-50 uppercase">Total learning time</th>
                                <th scope="col" className="px-6 py-3 text-start text-xs font-medium text-neutral-50 uppercase">Average session duration</th>
                                <th scope="col" className="px-6 py-3 text-start text-xs font-medium text-neutral-50 uppercase">Average session productivity</th>
                            </tr>
                            </thead>
                            <tbody className="divide-y divide-gray-200">
                            <tr>
                                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-800">{weeklySummary.topTopic}</td>
                                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-800">{weeklySummary.topObstacle}</td>
                                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-800">{totalHours} hours {totalMinutes} minutes</td>
                                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-800">{avgHours} hours {avgMinutes} minutes</td>
                                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-800">{weeklySummary.avgProductivity}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    )
}