import {useForm} from "react-hook-form";
import {useNavigate} from "react-router-dom";

export default function TimeSection() {
    const { register } = useForm();

    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        navigate('../feedback', { replace: true });
    }

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Start date
                <input
                    type="datetime-local"
                    {...register("Start date")}
                />
            </label>
            <label>
                Duration
                <input
                    type="text"
                    {...register("Duration")}
                />
            </label>
            <button type="submit">Next</button>
        </form>
    )
}