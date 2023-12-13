import HistoryTableRow from "./HistoryTableRow.jsx";

export default function HistoryTable({sessionArray}) {
    return (
        <table>
            <thead>
            <tr>
                <th>Topic</th>
                <th>Description</th>
                <th>Start Date</th>
                <th>Start Time</th>
                <th>Duration</th>
                <th>Obstacle</th>
                <th>Productivity</th>
            </tr>
            </thead>
            <tbody>
            {sessionArray && sessionArray.map((session, sessionID) => (
                <HistoryTableRow key={sessionID} session={session} />
            ))}
            </tbody>
        </table>
    )
}
