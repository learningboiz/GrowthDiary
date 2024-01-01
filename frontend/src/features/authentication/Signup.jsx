import {app} from "../../firebase.js";
import {useNavigate} from "react-router-dom";
import { getAuth, createUserWithEmailAndPassword } from "firebase/auth";
import {useState} from "react";

export default function Signup() {

    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const auth = getAuth();
    const handleSignUp = (e) => {
        e.preventDefault();

        createUserWithEmailAndPassword(auth, email, password)
            .then((userCredential) => {
                // Signed up
                const user = userCredential.user;
                // ...
            })
            .catch((error) => {
                const errorCode = error.code;
                const errorMessage = error.message;
                // ..
            });
    }


    return (
        <>
            <form onSubmit={handleSignUp}>
                <h1>
                    Sign-up
                </h1>
                <input
                    type="email"
                    placeholder="Enter your email"
                    onChange={(e) => setEmail(e.target.value)}
                ></input>
                <input
                    type="password"
                    placeholder="Enter your password"
                    onChange={(e) => setPassword(e.target.value)}
                ></input>
                <button type="submit">Sign-up</button>
            </form>
        </>
    )
}