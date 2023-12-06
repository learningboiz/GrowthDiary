import {useSessionForm} from "../hooks/useSessionForm.js";
import styles from "../../styles/tracker/sessionForm.module.css"
export default function DetailsForm({stepUpdater, newSession}) {
    const { saveInput, saveFormProgress } = useSessionForm(stepUpdater);
    const subheadingText = newSession? "What will you be working on today?" : "What did you work on previously?"

    return (
        <div className={styles.sessionForm}>
            <h2>Details</h2>
            <h3>{subheadingText}</h3>
            <form onSubmit={saveFormProgress}>
                <label>
                    Topic
                    <input
                        type="text"
                        name="topic"
                        placeholder="e.g. Spring Boot"
                        required={true}
                        onChange={saveInput}
                    />
                </label>
                <label>
                    Description
                    <input
                        type="text"
                        name="description"
                        placeholder="e.g. Building backend API with Spring MVC"
                        required={true}
                        onChange={saveInput}
                    />
                </label>
                <button type="submit">Next</button>
            </form>
        </div>
    )
}