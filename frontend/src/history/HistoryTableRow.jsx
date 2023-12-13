export default function HistoryTableRow({session}) {

    const details = session.details;
    const time = session.time;
    const feedback = session.feedback;

    return (
        <tr>
            <td>{details.topic}</td>
            <td>{details.description}</td>
            <td>{time.startDate}</td>
            <td>{time.startTime}</td>
            <td>{time.duration}</td>
            <td>{feedback.obstacle}</td>
            <td>{feedback.productivity}</td>
        </tr>
    )
}