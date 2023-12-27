import ProductivityFilter from "./ProductivityFilter.jsx";
import DateFilter from "./DateFilter.jsx";
import DurationFilter from "./DurationFilter.jsx";
import SolidButton from "../../../components/buttons/SolidButton.jsx";
import {useState} from "react";

export default function FilterModal({setHistoryDTO}) {

    const [filterRequest, setFilterRequest] = useState({});

    const handleApplyFilters = (e) => {
        e.preventDefault();
        console.log(filterRequest);
        setHistoryDTO((prev) => ({
            ...prev,
            filterRequest: filterRequest
        }));
    }

    return (
        <>
            <DateFilter setFilterRequest={setFilterRequest} />
            <DurationFilter setFilterRequest={setFilterRequest}/>
            <ProductivityFilter setFilterRequest={setFilterRequest}/>
            <SolidButton handleOnClick={handleApplyFilters} buttonText={"Apply"} />
        </>
    )
}