import historyAPI from "../api/historyAPI.js";
import {useEffect, useState} from "react";
import HistoryTable from "./HistoryTable.jsx";

export default function HistoryView() {

    const [sessionArray, setSessionArray] = useState();

    useEffect(() => {

        const fetchSessionData = async () => {
            const data = await historyAPI();
            const json = await data.json();

            setSessionArray(json.content)
            console.log(json);
        }
        fetchSessionData()
            .catch(console.error);
    }, []);

    return (
        <>
            <h2>Here is your session history</h2>
            <HistoryTable sessionArray={sessionArray} />
        </>
    )
}