import {Link} from "react-router-dom";

export default function NavigationBar() {
    return (
        <nav>
            <ul>
                <li>
                    <Link to="/">Home page</Link>
                </li>
                <li>
                    <Link to="about">About page</Link>
                </li>
                <li>
                    <Link to="session">Session page</Link>
                </li>
            </ul>
        </nav>
    )
}