import {useForm} from "react-hook-form";
import {useNavigate} from "react-router-dom";

export default function FeedbackSection() {
    const { register } = useForm();


    return (
        <form>
            <label>
                Key Obstacle
                <input
                    type="text"
                    {...register("Obstacle")}
                />
            </label>
            <label>
                Productivity
                <input
                    type="range"
                    min="1"
                    max="5"
                    {...register("Productivity")}
                />
            </label>
            <button type="submit">Next</button>
        </form>
    )
}