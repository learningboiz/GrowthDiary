import {useContext} from "react";
import {useForm} from "react-hook-form";
import {ErrorMessage} from "@hookform/error-message";

import {SessionContext} from "../SessionContext.jsx";


export default function PastTimeForm({stepUpdater}) {
    const {setSessionForm} = useContext(SessionContext);
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
    }


    return (
        <div className="py-8 px-4 mx-auto max-w-2xl lg:py-16">
            <h2 className="text-2xl font-bold mb-4 text-indigo-600">Time</h2>
            <h3 className="text-base -mt-2 mb-6 text-gray-600 border-gray-300 border-b pb-3">When did you work on this?</h3>

            <form onSubmit={handleSubmit(onSubmit)}>
                <div className="mb-4">
                    <label className="block text-sm font-medium mb-2 text-indigo-500">
                        Start Period
                    </label>
                        <input
                            type="datetime-local"
                            {...register("startPeriod", {
                                required: "Please provide a date and time"
                            })}
                            className="py-3 px-5 block w-full border border-gray-500 rounded-lg text-sm text-gray-500 focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50"
                        />
                        <div>
                            <ErrorMessage
                                errors={errors}
                                name="startPeriod"
                                render={({ message }) =>
                                    <p className="text-sm text-red-500 mt-2">{message}</p>}
                            />
                        </div>
                </div>

                <div className="mb-4">
                    <label className="block text-sm font-medium mb-2 text-indigo-500">
                        Duration
                    </label>
                        <div className="flex items-center gap-x-2">
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
                            className="py-3 px-5 w-20 border border-gray-500 rounded-lg text-sm text-gray-500 focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50"
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
                                    message: "Minutes should be non-negative"
                                },
                                max: {
                                    value: 59,
                                    message: "Minutes should not exceed 59"
                                },
                                validate: {
                                    moreThanZero: v => (v + (getValues().hours * 60)) > 0 || "Total duration should be greater than 0"
                                }
                            })}
                            className="py-3 px-5 w-20 border border-gray-500 rounded-lg text-sm text-gray-500 focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50"
                        />
                        <p>minutes(s)</p>
                    </div>
                        <div>
                            <ErrorMessage
                                errors={errors}
                                name="hours"
                                render={({ message }) =>
                                    <p className="text-sm text-red-500 mt-2">{message}</p>}
                            />
                        </div>
                        <div>
                            <ErrorMessage
                                errors={errors}
                                name="minutes"
                                render={({ message }) =>
                                    <p className="text-sm text-red-500 mt-2">{message}</p>}
                            />
                        </div>
                </div>
                <button type="submit"
                        className="py-2 px-3 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-indigo-500 text-white hover:bg-indigo-600">
                    Next</button>
            </form>
        </div>
    )
}