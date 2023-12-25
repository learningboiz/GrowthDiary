import {getFormattedDateTime} from "../utility/getFormattedDateTime.js";
import {getLocalDateTime} from "../utility/getLocalDateTime.js";
import {getHoursAndMinutes} from "../utility/getHoursAndMinutes.js";
import {getDescriptiveProductivity} from "../utility/getDescriptiveProductivity.js";

export default function HistoryTableRow({session}) {

    const details = session.details;
    const time = session.time;
    const feedback = session.feedback;

    // format time
    const localDateTime = getLocalDateTime(time.startDate, time.startTime);
    const { sessionDate, sessionTime } = getFormattedDateTime(localDateTime);
    const { hours, minutes } = getHoursAndMinutes(time.duration);
    const productivity = getDescriptiveProductivity(feedback.productivity);

    return (
        <tr>
            <td className="h-px w-px whitespace-nowrap">
                <div className="px-6 py-2">
                    <span className="text-sm font-medium text-gray-600">
                        {details.topic}
                    </span>
                </div>
            </td>

            <td className="h-px w-px whitespace-nowrap">
                <div className="px-6 py-2">
                    <span className="text-sm text-gray-600">
                        {details.description}
                    </span>
                </div>
            </td>

            <td className="h-px w-px whitespace-nowrap">
                <div className="px-6 py-2">
                    <span className="text-sm text-gray-600">
                        {sessionDate}
                    </span>
                </div>
            </td>

            <td className="h-px w-px whitespace-nowrap">
                <div className="px-6 py-2">
                    <span className="text-sm text-gray-600">
                        {sessionTime}
                    </span>
                </div>
            </td>

            <td className="h-px w-px whitespace-nowrap">
                <div className="px-6 py-2">
                    <span className="text-sm text-gray-600">
                        {hours} hours {minutes} minutes
                    </span>
                </div>
            </td>

            <td className="h-px w-px whitespace-nowrap">
                <div className="px-6 py-2">
                    <span className="text-sm text-gray-600">
                        {feedback.obstacle}
                    </span>
                </div>
            </td>

            <td className="h-px w-px whitespace-nowrap">
                <div className="px-6 py-2">
                    <span className="text-sm text-gray-600">
                        {productivity}
                    </span>
                </div>
            </td>
        </tr>
    )
}