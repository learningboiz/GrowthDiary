import {useEffect, useState} from "react";
import HistoryTable from "./HistoryTable.jsx";
import defaultHistoryAPI from "../api/defaultHistoryAPI.js";
import customHistoryAPI from "../api/customHistoryAPI.js";
import PageViewToggle from "./PageViewToggle.jsx";
import SortToggle from "./SortToggle.jsx";
import styles from "../styles/history/historyView.module.css";

export default function HistoryView() {

    const [sessionArray, setSessionArray] = useState();
    const [historyDTO, setHistoryDTO] = useState({});

    useEffect(() => {

        const fetchDefaultHistory = async () => {
            const data = await defaultHistoryAPI();
            const json = await data.json();

            setSessionArray(json.content)
            console.log(json);
        }
        fetchDefaultHistory()
            .catch(console.error);
    }, []);

    useEffect(() => {

        const fetchCustomHistory = async () => {
            const data = await customHistoryAPI(historyDTO);
            const json = await data.json();

            console.log(json);
            console.log(historyDTO);
            setSessionArray(json.content)
        }
        fetchCustomHistory()
            .catch(console.error);
    }, [historyDTO]);

    return (
        <div className={styles.historyView}>
            <h2>View your session history</h2>
            <div className={styles.toggles}>
                <SortToggle setHistoryDTO={setHistoryDTO} />
                <PageViewToggle setHistoryDTO={setHistoryDTO} />
            </div>
            <div className={styles.historyTable}>
                <HistoryTable sessionArray={sessionArray} />
            </div>
        </div>
    )
}