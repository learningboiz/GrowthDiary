import historyAPI from "../api/historyAPI.js";
import {useState} from "react";
import HistoryRow from "./HistoryRow.jsx";

export default function HistoryView() {

    const [sessionArray, setSessionArray] = useState();

    const handleOnClick = async (e) => {
        e.preventDefault();
        try {
            const apiResponse = await historyAPI();
            const jsonResponse = await apiResponse.json();
            const contentArray = jsonResponse.content;

            console.log(contentArray);

            setSessionArray(contentArray);
        } catch (error) {
            console.log(error);
        }
    }



    return (
        <>
            <h2>Here is your session history</h2>
            <button onClick={handleOnClick}>Get history data</button>
            {sessionArray &&
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
                        {sessionArray.map((session, sessionID) => (
                            <HistoryRow key={sessionID} session={session} />
                        ))}
                    </tbody>
                </table>
            }

        </>
    )
}