import {Link} from "react-router-dom";


export default function NavigationBar() {
    return (
        <nav className="flex justify-end p-4">
            <ul className="flex space-x-4">
                <li>
                    <Link className="text-indigo-500 hover:text-indigo-700 hover:font-medium transition duration-300"
                          to="/">Home</Link>
                </li>
                <li>
                    <Link className="text-indigo-500 hover:text-indigo-700 hover:font-medium transition duration-300"
                        to="about">About</Link>
                </li>
                <li>
                    <Link className="text-indigo-500 hover:text-indigo-700 hover:font-medium transition duration-300"
                          to="session">Session</Link>
                </li>
                <li>
                    <Link className="text-indigo-500 hover:text-indigo-700 hover:font-medium transition duration-300"
                          to="history">History</Link>
                </li>
                <li>
                    <Link className="text-indigo-500 hover:text-indigo-700 hover:font-medium transition duration-300"
                          to="analytics">Analytics</Link>
                </li>
            </ul>
        </nav>
    )
}