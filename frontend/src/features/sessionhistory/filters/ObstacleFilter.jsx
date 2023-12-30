import {obstacleList} from "../../sessiontracker/utility/obstacleList.js";
import {useState} from "react";
import CheckboxInput from "../../../components/inputfields/CheckboxInput.jsx";

export default function ObstacleFilter({setFilterRequest, obstacleInput, setObstacleInput}) {

    const handleOnChange = (e) => {

        const selectedOption = e.target.value;

        const optionInArray = obstacleInput.includes(selectedOption);

        let updatedArray; // created a scoped array for immediate value change since state doesnt updated immediately

        if (optionInArray) {
            updatedArray = obstacleInput.filter(obstacle => obstacle !== selectedOption)
        } else {
            updatedArray = [
                ...obstacleInput,
                selectedOption
            ]
        }

        setObstacleInput(updatedArray);


        setFilterRequest((prev) => ({
            ...prev,
            feedbackFilter: {
                ...prev.feedbackFilter,
                obstacles: updatedArray
            }
        }));
    }



    return (
        <>
            <h2 className="text-sm mb-3 font-medium text-indigo-500 border-gray-300 border-b pb-1">Obstacle</h2>
            <CheckboxInput
                inputArrayState={obstacleInput}
                handleOnChange={handleOnChange}
                optionsArray={obstacleList}/>
        </>
    )
}