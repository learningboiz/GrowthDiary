// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getAuth } from "firebase/auth";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyCncbQIdJ0-7tllzIEkBeH6KSz3XlQWKsc",
    authDomain: "learnersflow.firebaseapp.com",
    projectId: "learnersflow",
    storageBucket: "learnersflow.appspot.com",
    messagingSenderId: "7755115532",
    appId: "1:7755115532:web:5007aea51ec70fe5b05229",
    measurementId: "G-8Q66XEMC3B"
};

// Initialize Firebase
export const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
const analytics = getAnalytics(app);