import NavigateSessionButton from "../buttons/NavigateSessionButton.jsx";
import styles from "../../styles/tracker/sessionPage.module.css";

export default function MainSessionPage() {
    return (
        <div className={styles.sessionPage}>
            <h2>Track your learning session</h2>
            <NavigateSessionButton newSession={true} />
            <NavigateSessionButton newSession={false} />
        </div>
    )
}
