import {obstacleList} from "../utility/obstacleList.js";
import styles from "../../styles/tracker/sessionForm.module.css"
import {useContext} from "react";
import {SessionContext} from "../SessionContext.jsx";
import {useForm} from "react-hook-form";
import {ErrorMessage} from "@hookform/error-message";

export default function FeedbackForm({stepUpdater}) {
    const {sessionForm, setSessionForm} = useContext(SessionContext);
    const { register,
        handleSubmit,
        formState: {errors},
    } = useForm();

    const onSubmit = (data) => {
        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            obstacle: data.obstacle,
            productivity: data.productivity,
        }))
        stepUpdater();

        console.log(sessionForm)
    }

    return (
        <div className={styles.sessionForm}>
            <h2>Feedback</h2>
            <h3>How did the session go?</h3>
            <form onSubmit={handleSubmit(onSubmit)}>
                <label>
                    Key Obstacle
                    <select {...register("obstacle", {
                        required: "Please pick an obstacle"
                    })}
                        defaultValue=""
                    >
                        <option value="" disabled>
                            Please pick an option below
                        </option>
                        {obstacleList.map((obstacle) => (
                            <option key={obstacle}>{obstacle}</option>
                            )
                        )}
                    </select>
                </label>
                <div className={styles.sessionFormError}>
                    <ErrorMessage
                        errors={errors}
                        name="obstacle"
                        render={({ message }) => <p>{message}</p>}
                    />
                </div>
                <label>
                    Productivity
                    <input
                        type="range"
                        min="1"
                        max="5"
                        step="1"
                        defaultValue="3"
                        {...register("productivity", {
                            required: "Please provide a productivity rating",
                        })}
                    />
                </label>
                <div className={styles.sessionFormError}>
                    <ErrorMessage
                        errors={errors}
                        name="productivity"
                        render={({ message }) => <p>{message}</p>}
                    />
                </div>
                <button type="submit">Complete</button>
            </form>
        </div>
    )
}