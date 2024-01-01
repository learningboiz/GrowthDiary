import { getAuth, signOut } from "firebase/auth";
import {useNavigate} from "react-router-dom";

export default function LogOut() {

    const navigate = useNavigate();

    const auth = getAuth();

    const handleLogOut = () => {
        signOut(auth).then(() => {
            navigate("/")
            console.log("signed out successfully")
            // Sign-out successful.
        }).catch((error) => {
            // An error happened.
        });
    }

    return <button onClick={handleLogOut}>Log out</button>
}