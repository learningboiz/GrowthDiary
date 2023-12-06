import {useSessionForm} from "../hooks/useSessionForm.js";
import {obstacleList} from "../utility/obstacleList.js";
import styles from "../../styles/tracker/sessionForm.module.css"

export default function FeedbackForm({stepUpdater}) {
    const { saveInput, saveFormProgress } = useSessionForm(stepUpdater);

    return (
        <div className={styles.sessionForm}>
            <h2>Feedback</h2>
            <h3>How did the session go?</h3>
            <form onSubmit={saveFormProgress}>
                <label>
                    Key Obstacle
                    <select name="obstacle"
                            required={true}
                            onChange={saveInput}>
                        {obstacleList.map((obstacle) => (
                            <option key={obstacle}>{obstacle}</option>
                            )
                        )}
                    </select>
                </label>
                <label>
                    Productivity
                    <input
                        type="range"
                        min="1"
                        max="5"
                        step="1"
                        name="productivity"
                        required={true}
                        onChange={saveInput}
                    />
                </label>
                <button type="submit">Complete</button>
            </form>
        </div>
    )
}