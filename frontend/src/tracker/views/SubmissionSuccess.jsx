import {Link} from "react-router-dom";

export default function SubmissionSuccess() {


    return (
        <>
            <h3>Your session has been successfully recorded</h3>
            <Link to="/session">Return to session page</Link>
        </>
    )
}