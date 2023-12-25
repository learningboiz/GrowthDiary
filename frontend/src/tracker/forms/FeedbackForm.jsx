import {useContext, useState} from "react";
import {useForm} from "react-hook-form";
import {ErrorMessage} from "@hookform/error-message";

import {SessionContext} from "../SessionContext.jsx";

import {obstacleList} from "../utility/obstacleList.js";
import {productivityDescriptions} from "../utility/productivityDescription.js";

export default function FeedbackForm({stepUpdater}) {
    const {setSessionForm} = useContext(SessionContext);
    const { register,
        handleSubmit,
        formState: {errors},
        setValue,
    } = useForm();
    const [rating, setRating] = useState("Moderate");

    const onSubmit = (data) => {
        setSessionForm((prevSessionForm) => ({
            ...prevSessionForm,
            obstacle: data.obstacle,
            productivity: data.productivity,
        }))
        stepUpdater();
    }

    const handleProductivityChange = (e) => {
        const rating = e.target.value;
        setValue("productivity", rating);
        const ratingDescription = productivityDescriptions[rating];
        setRating(ratingDescription);
    }

    return (
        <div className="py-8 px-4 mx-auto max-w-2xl lg:py-16">
            <h2 className="text-2xl font-bold mb-4 text-indigo-600">Feedback</h2>
            <h3 className="text-base -mt-2 mb-6 text-gray-600 border-gray-300 border-b pb-3">How did the session go?</h3>

            <form onSubmit={handleSubmit(onSubmit)}>

                <div className="mb-4">
                    <label className="block text-sm font-medium mb-2 text-indigo-500">
                        Key Obstacle
                    </label>
                    <select {...register("obstacle", {
                        required: "Please pick an obstacle"
                    })}
                            defaultValue=""
                            className="py-3 px-5 block w-full border border-gray-500 rounded-lg text-sm text-gray-600 focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50"
                    >
                        <option value="" disabled>
                            Please pick an option below
                        </option>
                        {obstacleList.map((obstacle) => (
                                <option key={obstacle}>{obstacle}</option>
                            )
                        )}
                    </select>

                    <div>
                        <ErrorMessage
                            errors={errors}
                            name="obstacle"
                            render={({ message }) =>
                                <p className="text-sm text-red-600 mt-2">{message}</p>}
                        />
                    </div>
                </div>

                <div className="mb-4">
                    <label className="block text-sm font-medium mb-2 text-indigo-500">
                        Productivity
                    </label>
                        <input
                            type="range"
                            min="1"
                            max="5"
                            step="1"
                            defaultValue="3"
                            {...register("productivity", {
                                onChange: handleProductivityChange,
                                required: "Please provide a productivity rating",
                            })}
                            className="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer
                            [&::-webkit-slider-thumb]:w-2.5
                            [&::-webkit-slider-thumb]:h-2.5
                            [&::-webkit-slider-thumb]:-mt-0.5
                            [&::-webkit-slider-thumb]:appearance-none
                            [&::-webkit-slider-thumb]:bg-white
                            [&::-webkit-slider-thumb]:shadow-[0_0_0_4px_rgba(37,99,235,1)]
                            [&::-webkit-slider-thumb]:rounded-full
                            [&::-webkit-slider-thumb]:transition-all
                            [&::-webkit-slider-thumb]:duration-150
                            [&::-webkit-slider-thumb]:ease-in-out"
                        />
                </div>
                <div className="flex justify-between">
                    <span className="text-sm font-light -mt-2 mb-6 text-indigo-500">Very Low</span>
                    <span className="text-sm font-light -mt-2 mb-6 text-indigo-500">Low</span>
                    <span className="text-sm font-light -mt-2 mb-6 text-indigo-500">Moderate</span>
                    <span className="text-sm font-light -mt-2 mb-6 text-indigo-500">High</span>
                    <span className="text-sm font-light -mt-2 mb-6 text-indigo-500">Very High</span>
                </div>

                <div>
                    <ErrorMessage
                        errors={errors}
                        name="productivity"
                        render={({ message }) =>
                            <p className="text-sm text-red-600 mt-2">{message}</p>}
                    />
                </div>
                <button type="submit"
                        className="py-2 px-3 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-indigo-500 text-white hover:bg-indigo-600">
                    Next</button>
            </form>
        </div>
    )
}