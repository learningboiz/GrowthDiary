import {useContext} from "react";
import {useForm} from "react-hook-form";
import {ErrorMessage} from "@hookform/error-message";

import styles from "../../styles/tracker/sessionForm.module.css"
import {SessionContext} from "../SessionContext.jsx";


export default function PastTimeForm({stepUpdater}) {
    const {sessionForm, setSessionForm} = useContext(SessionContext);
    const { register,
        handleSubmit,
        formState: {errors},
        getValues
    } = useForm();

    const onSubmit = (data) => {
        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            startPeriod: new Date(data.startPeriod),
            hours: data.hours,
            minutes: data.minutes
        }))
        stepUpdater();

        console.log(sessionForm)
    }


    return (
        <div className={styles.sessionForm}>
            <h2>Time</h2>
            <h3>When did you work on this?</h3>
            <form onSubmit={handleSubmit(onSubmit)}>
                <label>
                    Start Period
                    <input
                        type="datetime-local"
                        {...register("startPeriod", {
                            required: "Please provide a date and time"
                        })}
                    />
                    <div className={styles.sessionFormError}>
                        <ErrorMessage
                            errors={errors}
                            name="startPeriod"
                            render={({ message }) => <p>{message}</p>}
                        />
                    </div>
                </label>
                <label>
                    Duration
                    <span>
                        <input
                            type="number"
                            placeholder="1"
                            {...register("hours", {
                                valueAsNumber: true,
                                required: "Please indicate the hours spent",
                                min: {
                                    value: 0,
                                    message: "Hours should be non-negative"
                                }
                            })}
                        />
                        <p>hour(s)</p>
                        <input
                            type="number"
                            placeholder="45"
                            {...register("minutes", {
                                valueAsNumber: true,
                                required: "Please indicate the minutes spent",
                                min: {
                                    value: 0,
                                    message: "Hours should be non-negative"
                                },
                                validate: {
                                    moreThanZero: v => (v + (getValues().hours * 60)) > 0 || "Total duration should be greater than 0"
                                }
                            })}
                        />
                        <p>minutes(s)</p>
                    </span>
                    <div className={styles.sessionFormError}>
                        <ErrorMessage
                            errors={errors}
                            name="hours"
                            render={({ message }) => <p>{message}</p>}
                        />
                    </div>
                    <div className={styles.sessionFormError}>
                        <ErrorMessage
                            errors={errors}
                            name="minutes"
                            render={({ message }) => <p>{message}</p>}
                        />
                    </div>
                </label>
                <button type="submit">Next</button>
            </form>
        </div>
    )
}