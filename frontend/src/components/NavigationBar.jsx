import {Link} from "react-router-dom";
import styles from '../styles/navigationbar.module.css';

export default function NavigationBar() {
    return (
        <nav>
            <ul className={styles.navigationBar}>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="about">About</Link>
                </li>
                <li>
                    <Link to="session">Session</Link>
                </li>
            </ul>
        </nav>
    )
}