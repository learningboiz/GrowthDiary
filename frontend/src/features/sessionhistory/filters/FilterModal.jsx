import ProductivityFilter from "./ProductivityFilter.jsx";
import DateFilter from "./DateFilter.jsx";
import DurationFilter from "./DurationFilter.jsx";
import ObstacleFilter from "./ObstacleFilter.jsx";
import DescriptionFilter from "./DescriptionFilter.jsx";
import TopicFilter from "./TopicFilter.jsx";
import SolidButton from "../../../components/buttons/SolidButton.jsx";
import OutlineButton from "../../../components/buttons/OutlineButton.jsx"
import {useState} from "react";

export default function FilterModal({setHistoryDTO, handleToggleClose}) {

    const [filterRequest, setFilterRequest] = useState({});

    const [topicInput, setTopicInput] = useState("");
    const [descriptionInput, setDescriptionInput] = useState("");
    const [dateInput, setDateInput] = useState();
    const [durationInput, setDurationInput] = useState();
    const [productivityInput, setProductivityInput] = useState();
    const [obstacleInput, setObstacleInput] = useState([]);

    const handleApplyFilters = (e) => {
        e.preventDefault();
        console.log(filterRequest);
        setHistoryDTO((prev) => ({
            ...prev,
            filterRequest: filterRequest
        }));
        handleToggleClose();
    }

    const handleResetFilters = (e) => {
        e.preventDefault();

        setTopicInput("");
        setDescriptionInput("");
        setDateInput(null);
        setDurationInput(null);
        setProductivityInput(null);
        setObstacleInput([]);


        setFilterRequest({});

        setHistoryDTO((prev) => ({
            ...prev,
            filterRequest: {}
        }))
    }
    return (
        <div className="p-4">

            <TopicFilter
                topicInput={topicInput}
                setTopicInput={setTopicInput}
                setFilterRequest={setFilterRequest} />

            <div className="mt-4">
                <DescriptionFilter
                    descriptionInput={descriptionInput}
                    setDescriptionInput={setDescriptionInput}
                    setFilterRequest={setFilterRequest}/>
            </div>

            <div className="mt-4">
                <DateFilter
                    dateInput={dateInput}
                    setDateInput={setDateInput}
                    setFilterRequest={setFilterRequest} />
            </div>

            <div className="mt-4">
                <DurationFilter
                    durationInput={durationInput}
                    setDurationInput={setDurationInput}
                    setFilterRequest={setFilterRequest}/>
            </div>

            <div className="mt-4">
                <ProductivityFilter
                    productivityInput={productivityInput}
                    setProductivityInput={setProductivityInput}
                    setFilterRequest={setFilterRequest}/>
            </div>

            <div className="mt-4">
                <ObstacleFilter
                    obstacleInput={obstacleInput}
                    setObstacleInput={setObstacleInput}
                    setFilterRequest={setFilterRequest}/>
            </div>

            <div className="flex flex-row justify-center mt-6">
                <div className="mr-2">
                    <SolidButton handleOnClick={handleApplyFilters} buttonText={"Apply"} />
                </div>
                <OutlineButton handleOnClick={handleResetFilters} buttonText={"Reset All"} />
            </div>
        </div>
    )
}