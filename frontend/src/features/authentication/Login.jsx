import {getAuth, signInWithEmailAndPassword} from "firebase/auth";
import {useState} from "react";
import {useNavigate} from "react-router-dom";

export default function Login() {

    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogIn = (e) => {
        e.preventDefault();
        const auth = getAuth();
        signInWithEmailAndPassword(auth, email, password)
            .then((userCredential) => {
                // Signed in
                const user = userCredential.user;
                navigate("/home")
                // ...
            })
            .catch((error) => {
                const errorCode = error.code;
                const errorMessage = error.message;
            });
    }

    return (
        <>
            <form onSubmit={handleLogIn}>
                <h1>
                    Log in
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
                <button type="submit">Log-in</button>
            </form>
        </>
    )


}