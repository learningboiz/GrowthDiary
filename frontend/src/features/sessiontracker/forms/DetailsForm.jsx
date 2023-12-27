import {useContext} from "react";
import {useForm} from "react-hook-form";
import {ErrorMessage} from "@hookform/error-message";

import {SessionContext} from "../SessionContext.jsx";
import SolidButton from "../../../components/buttons/SolidButton.jsx";


export default function DetailsForm({stepUpdater, newSession}) {
    const {sessionForm, setSessionForm} = useContext(SessionContext);
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
        console.log(sessionForm);
    }

    return (
        <div className="py-8 px-4 mx-auto max-w-2xl lg:py-16">
            <h2 className="text-2xl font-bold mb-4 text-indigo-600">Details</h2>
            <h3 className="text-base -mt-2 mb-6 text-gray-600 border-gray-300 border-b pb-3">{subheadingText}</h3>

            <form onSubmit={handleSubmit(onSubmit)}>
                <div className="mb-4">
                    <label className="block text-sm font-medium mb-2 text-indigo-500">
                        Topic
                    </label>
                    <input
                        type="text"
                        id="topic"
                        placeholder="e.g Spring Boot"
                        className="py-3 px-5 block w-full border border-gray-500 rounded-lg text-sm text-gray-500 focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50"
                        {...register("topic", {
                            required: "Please provide a topic",
                            minLength: {
                                value: 2,
                                message: "Topic must be longer than 2 characters",
                            },
                            maxLength: {
                                value: 50,
                                message: "Topic must be shorter than 50 characters",
                            },
                        })}
                    />

                    <div>
                        <ErrorMessage
                            errors={errors}
                            name="topic"
                            render={({ message }) => <p className="text-sm text-red-500 mt-2">{message}</p>}
                        />
                    </div>
                </div>

                <div className="mb-4">
                    <label className="block text-sm font-medium mb-2 text-indigo-500">
                        Description
                    </label>
                    <input
                        type="text"
                        id="description"
                        placeholder="e.g Setting up controller methods"
                        className="py-3 px-5 block w-full border border-gray-500 rounded-lg text-sm text-gray-500 focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50"
                        {...register("description", {
                            required: "Please provide a description",
                            minLength: {
                                value: 2,
                                message: "Description must be longer than 10 characters",
                            },
                            maxLength: {
                                value: 150,
                                message: "Description must be shorter than 150 characters",
                            },
                        })}
                    />
                    <div>
                        <ErrorMessage
                            errors={errors}
                            name="description"
                            render={({ message }) => <p className="text-sm text-red-500 mt-2">{message}</p>}
                        />
                    </div>
                </div>

                <SolidButton buttonText={"Next"} />
            </form>
        </div>
    );
}