import SingleSelect from "../../../components/inputfields/SingleSelect.jsx";
import {obstacleList} from "../../sessiontracker/utility/obstacleList.js";

export default function ObstacleFilter() {
    return (
        <SingleSelect placeholder={"Pick an obstacle"} optionList={obstacleList}/>
    )
}