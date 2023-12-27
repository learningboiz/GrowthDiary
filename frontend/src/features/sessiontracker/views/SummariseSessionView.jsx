import {useContext, useState} from "react";
import {SessionContext} from "../SessionContext.jsx";
import sessionAPI from "../sessionAPI.js";
import getSessionAPIFormat from "../utility/getSessionAPIFormat.js";
import AfterSessionView from "./AfterSessionView.jsx";
import {useSessionForm} from "../hooks/useSessionForm.js";
import SolidButton from "../../../components/buttons/SolidButton.jsx";

export default function SummariseSessionView() {
    const { sessionForm } = useContext(SessionContext);
    const { submissionMessages, parseFormSummary } = useSessionForm();
    const formSummary = parseFormSummary(sessionForm);

    const [formSubmitted, setFormSubmitted] = useState(false)
    const [errorMessage, setErrorMessage] = useState(null);

    const submitForm = async (e) => {
        e.preventDefault();
        console.log(sessionForm);

        const formattedData = getSessionAPIFormat(sessionForm);

        try {
            const apiResponse = await sessionAPI(formattedData);
            console.log(apiResponse);
            if (apiResponse.ok) {
                setFormSubmitted(true)
            } else {
                setErrorMessage("There's an issue with the data provided.")
            }
        } catch (error) {
            setErrorMessage("There was an issue with the network.")
        }
    }

    return (
        <>
            {!formSubmitted && !errorMessage &&
                <div className="py-8 px-4 mx-auto max-w-2xl lg:py-16">
                    <h2 className="text-2xl font-bold mb-4 text-indigo-600">Your session summary</h2>

                    <table className="divide-y divide-gray-200 border border-gray-300 mb-6">
                        <tbody className="divide-y divide-gray-200">

                        <tr>
                            <th
                                scope="col"
                                className="px-2 py-2 text-center text-xs font-medium uppercase bg-indigo-400">
                                <div className="flex items-center justify-center">
                                    <span className="text-xs font-semibold uppercase tracking-wide text-neutral-50">
                                    Worked on
                                    </span>
                                </div>
                            </th>
                            <td className="h-px w-px whitespace-nowrap text-start">
                                <div className="px-6 py-2">
                                    <span className="text-sm font-medium text-gray-500">
                                        {formSummary.topic} by {formSummary.description}
                                    </span>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th
                                scope="col"
                                className="px-2 py-2 text-center text-xs font-medium uppercase bg-indigo-400">
                                <div className="flex items-center justify-center">
                                    <span className="text-xs font-semibold uppercase tracking-wide text-neutral-50">
                                    Began on
                                    </span>
                                </div>
                            </th>
                            <td className="h-px w-px whitespace-nowrap text-start">
                                <div className="px-6 py-2">
                                    <span className="text-sm font-medium text-gray-500">
                                        {formSummary.sessionDate} {formSummary.sessionTime}
                                    </span>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th
                                scope="col"
                                className="px-2 py-2 text-center text-xs font-medium uppercase bg-indigo-400">
                                <div className="flex items-center justify-center">
                                    <span className="text-xs font-semibold uppercase tracking-wide text-neutral-50">
                                    Lasted for
                                    </span>
                                </div>
                            </th>
                            <td className="h-px w-px whitespace-nowrap text-start">
                                <div className="px-6 py-2">
                                    <span className="text-sm font-medium text-gray-500">
                                        {formSummary.hours} hour(s) {formSummary.minutes} minute(s)
                                    </span>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th
                                scope="col"
                                className="px-2 py-2 text-center text-xs font-medium uppercase bg-indigo-400">
                                <div className="flex items-center justify-center">
                                    <span className="text-xs font-semibold uppercase tracking-wide text-neutral-50">
                                    Biggest challenge
                                    </span>
                                </div>
                            </th>
                            <td className="h-px w-px whitespace-nowrap text-start">
                                <div className="px-6 py-2">
                                    <span className="text-sm font-medium text-gray-500">
                                        {formSummary.obstacle}
                                    </span>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th
                                scope="col"
                                className="px-2 py-2 text-center text-xs font-medium uppercase bg-indigo-400">
                                <div className="flex items-center justify-center">
                                    <span className="text-xs font-semibold uppercase tracking-wide text-neutral-50">
                                    Productivity rating
                                    </span>
                                </div>
                            </th>
                            <td className="h-px w-px whitespace-nowrap text-start">
                                <div className="px-6 py-2">
                                    <span className="text-sm font-medium text-gray-500">
                                        {formSummary.productivityDescription}
                                    </span>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <SolidButton handleOnClick={submitForm} buttonText={"Submit"} />
                </div>
            }
            {formSubmitted && <AfterSessionView header={submissionMessages.successHeader} message={submissionMessages.successMessage}/>}
            {errorMessage && <AfterSessionView header={submissionMessages.errorHeader} message={errorMessage} />}
        </>
    )
}
