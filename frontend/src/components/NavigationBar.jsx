import {Link} from "react-router-dom";
import styles from "../styles/components/navigationBar.module.css";


export default function NavigationBar() {
    return (
        <nav className={styles.navigationBar}>
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="about">About</Link>
                </li>
                <li>
                    <Link to="session">Session</Link>
                </li>
                <li>
                    <Link to="history">History</Link>
                </li>
            </ul>
        </nav>
    )
}