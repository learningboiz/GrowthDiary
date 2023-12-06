import {useSessionForm} from "../hooks/useSessionForm.js";
import styles from "../../styles/tracker/sessionForm.module.css"

export default function PastTimeForm({stepUpdater}) {
    const { saveInput, saveTimeInput, saveFormProgress } = useSessionForm(stepUpdater);


    return (
        <div className={styles.sessionForm}>
            <h2>Time</h2>
            <h3>When did you work on this?</h3>
            <form onSubmit={saveFormProgress}>
                <label>
                    Start period
                    <input
                        type="datetime-local"
                        name="startPeriod"
                        required={true}
                        onChange={saveTimeInput}
                    />
                </label>
                <label>
                    Duration
                    <input
                        type="number"
                        name="duration"
                        required={true}
                        onChange={saveInput}
                    />
                </label>
                <button type="submit">Next</button>
            </form>
        </div>
    )
}