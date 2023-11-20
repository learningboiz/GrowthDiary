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

    function handleFormUpdate(e) {
        setForm({
            ...form,
            [e.target.name] : e.target.value
        });
    }

    function handleSegmentUpdate() {
        setCurrentSegment(currentSegment + 1);
    }

    const renderDetailsSegment = () => (
        <>
            <label>Skill
                <input type="text" name="skill" value ={form.skill} onChange={handleFormUpdate} />
            </label>
            <label>Description
                <input type="text" name="description" value ={form.description} onChange={handleFormUpdate} />
            </label>
            <button type="button" onClick={handleSegmentUpdate}>Next</button>
        </>
    )

    const renderTimeSegment = () => (
        <>
            <label>Start Period
                <input type="datetime-local" name="startPeriod" value ={form.startPeriod} onChange={handleFormUpdate} />
            </label>
            <label>End Period
                <input type="datetime-local" name="endPeriod" value ={form.endPeriod} onChange={handleFormUpdate} />
            </label>
            <button type="button" onClick={handleSegmentUpdate}>Next</button>
        </>
    )

    const renderFeedbackSegment = () => (
        <>
            <label>Key Distraction
                <input type="text" name="distraction" value ={form.distraction} onChange={handleFormUpdate} />
            </label>
            <label>Productivity rating
                <input type="range" min="1" max="5" name="productivity" value ={form.productivity} onChange={handleFormUpdate} />
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