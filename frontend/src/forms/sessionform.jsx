import {useState} from "react";

export default function SessionForm() {

    const [form, setForm] = useState({
        skill: '',
        description: '',
        startPeriod: '',
        endPeriod: '',
        distraction: '',
        productivity: '',
    });

    const [currentSegment, setCurrentSegment] = useState(1);
    const [errors, setErrors] = useState({});

    function handleFormUpdate(e) {
        setForm({
            ...form,
            [e.target.name] : e.target.value
        });

        setErrors({
            ...errors,
            [e.target.name]: "",
        });
    }

    function handleSegmentUpdate(e) {
        e.preventDefault();

        // Validate the form based on the current segment
        if (currentSegment === 1) {
            if (!form.skill || !form.description) {
                setErrors({
                    skill: !form.skill ? "Skill is required" : "",
                    description: !form.description ? "Description is required" : "",
                });
                return;
            }
        } else if (currentSegment === 2) {
            if (!form.startPeriod || !form.endPeriod) {
                setErrors({
                    startPeriod: !form.startPeriod ? "Start Period is required" : "",
                    endPeriod: !form.endPeriod ? "End Period is required" : "",
                });
                return;
            }
        } else if (currentSegment === 3) {
            if (!form.distraction || !form.productivity) {
                setErrors({
                    distraction: !form.distraction ? "Distraction is required" : "",
                    productivity: !form.productivity
                        ? "Productivity is required"
                        : "",
                });
                return;
            }
        }

        // If validation passes, move to the next segment
        setCurrentSegment(currentSegment + 1);
    }

    const renderDetailsSegment = () => (
        <>
            <label>Skill
                <input type="text" name="skill" value ={form.skill} onChange={handleFormUpdate} />
                <div className="error">{errors.skill}</div>
            </label>
            <label>Description
                <input type="text" name="description" value ={form.description} onChange={handleFormUpdate} />
                <div className="error">{errors.description}</div>
            </label>
            <button type="button" onClick={handleSegmentUpdate}>Next</button>
        </>
    )

    const renderTimeSegment = () => (
        <>
            <label>Start Period
                <input type="datetime-local" name="startPeriod" value ={form.startPeriod} onChange={handleFormUpdate} />
                <div className="error">{errors.startPeriod}</div>
            </label>
            <label>End Period
                <input type="datetime-local" name="endPeriod" value ={form.endPeriod} onChange={handleFormUpdate} />
                <div className="error">{errors.endPeriod}</div>
            </label>
            <button type="button" onClick={handleSegmentUpdate}>Next</button>
        </>
    )

    const renderFeedbackSegment = () => (
        <>
            <label>Key Distraction
                <input type="text" name="distraction" value ={form.distraction} onChange={handleFormUpdate} />
                <div className="error">{errors.distraction}</div>
            </label>
            <label>Productivity rating
                <input type="range" min="1" max="5" name="productivity" value ={form.productivity} onChange={handleFormUpdate} />
                <div className="error">{errors.productivity}</div>
            </label>
            <button type="button" onClick={handleSegmentUpdate}>Next</button>
        </>
    )

    return (
        <form>
            {currentSegment === 1 && renderDetailsSegment()}
            {currentSegment === 2 && renderTimeSegment()}
            {currentSegment === 3 && renderFeedbackSegment()}
        </form>
    )
}