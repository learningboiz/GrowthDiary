import {getFormattedDateTime} from "./utility/getFormattedDateTime.js";
import {getLocalDateTime} from "./utility/getLocalDateTime.js";
import {getHoursAndMinutes} from "./utility/getHoursAndMinutes.js";
import {getDescriptiveProductivity} from "./utility/getDescriptiveProductivity.js";

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
            <td>{details.topic}</td>
            <td>{details.description}</td>
            <td>{sessionDate}</td>
            <td>{sessionTime}</td>
            <td>{hours} hours {minutes} minutes</td>
            <td>{feedback.obstacle}</td>
            <td>{productivity}</td>
        </tr>
    )
}