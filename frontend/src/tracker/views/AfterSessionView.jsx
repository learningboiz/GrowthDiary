import {useNavigate} from "react-router-dom";
import styles from "../../styles/tracker/sessionPage.module.css";

export default function AfterSessionView({header, message}) {

    const navigate = useNavigate();

    const handleReturnButton = (e) => {
        e.preventDefault();
        navigate('/session');
    }

    return (
        <div className={styles.sessionPage}>
            <h2>{header}</h2>
            <h3>{message}</h3>
            <button className={styles.sessionPageButton} onClick={handleReturnButton}>Return to session page</button>
        </div>
    )
}