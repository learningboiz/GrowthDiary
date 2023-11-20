import {Link, Outlet} from "react-router-dom";

export default function SessionPage() {
    return (
        <>
            <h2>Session here</h2>
            <nav>
                <ul>
                    <li>
                        <Link to="new">New session</Link>
                    </li>
                    <li>
                        <Link to="old">Old session</Link>
                    </li>
                </ul>
            </nav>
        </>
    )
}
