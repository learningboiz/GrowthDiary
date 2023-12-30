import FilterModal from "../sessionhistory/filters/FilterModal.jsx";
import CheckboxSelect from "../../components/inputfields/CheckboxSelect.jsx";
import {obstacleList} from "../sessiontracker/utility/obstacleList.js";

export default function AboutPage() {

    let obstacleArray = [];

    const handleCheckedBox = (e) => {
        const isChecked = e.target.checked;
        if (isChecked) {
            console.log("The " + e.target.value + " option has been added")
            obstacleArray.push(e.target.value)
            console.log("The current array items are: " + obstacleArray)
        }

        if (!isChecked) {
            console.log("e.target.value" + " is removed")
            const index = obstacleArray.indexOf(e.target.value);
            obstacleArray.splice(index, 1);
            console.log("updated array is: " + obstacleArray)
        }
    }

    return (
        <>
            <h2>This is the about page</h2>
            <FilterModal />
            <CheckboxSelect handleOnClick={handleCheckedBox} optionsArray={obstacleList}/>
        </>
    )
}