import {useContext} from "react";
import {useForm} from "react-hook-form";
import {ErrorMessage} from "@hookform/error-message";

import styles from "../../styles/tracker/sessionForm.module.css"
import {SessionContext} from "../SessionContext.jsx";


export default function DetailsForm({stepUpdater, newSession}) {
    const {setSessionForm} = useContext(SessionContext);
    const subheadingText = newSession? "What will you be working on today?" : "What did you work on previously?"
    const { register,
            handleSubmit,
            formState: {errors}
    } = useForm();

    const onSubmit = (data) => {
        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            topic: data.topic,
            description: data.description
        }))
        stepUpdater();
    }

    return (
        <div className={styles.sessionForm}>
            <h2>Details</h2>
            <h3>{subheadingText}</h3>
            <form onSubmit={handleSubmit(onSubmit)}>
                <label>
                    Topic
                    <input
                        type="text"
                        placeholder="e.g Spring Boot"
                        {...register("topic", {
                            required: "Please provide a topic",
                            minLength : {
                                value: 2,
                                message: "Topic must be longer than 2 characters"
                            },
                            maxLength : {
                                value: 50,
                                message: "Topic must be shorter than 50 characters"
                            }
                        })}
                    />
                    <div className={styles.sessionFormError}>
                        <ErrorMessage
                            errors={errors}
                            name="topic"
                            render={({ message }) => <p>{message}</p>}
                        />
                    </div>

                </label>
                <label>
                    Description
                    <input
                        type="text"
                        placeholder="e.g Setting up controller methods"
                        {...register("description", {
                            required: "Please provide a description",
                            minLength : {
                                value: 2,
                                message: "Topic must be longer than 10 characters"
                            },
                            maxLength : {
                                value: 150,
                                message: "Topic must be shorter than 150 characters"
                            }
                        })}
                    />
                    <div className={styles.sessionFormError}>
                        <ErrorMessage
                            errors={errors}
                            name="description"
                            render={({ message }) => <p>{message}</p>}
                        />
                    </div>
                </label>
                <button type="submit">Next</button>
            </form>
        </div>
    )
}