import {useForm} from "react-hook-form";
import {useNavigate} from "react-router-dom";

export default function DetailsSection() {
    const { register } = useForm();
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        navigate('../time', {});
    }

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Topic
                <input
                    type="text"
                    {...register("Topic")}
                />
            </label>
            <label>
                Description
                <input
                    type="text"
                    {...register("Description")}
                />
            </label>
            <button type="submit">Next</button>
        </form>
    )
}