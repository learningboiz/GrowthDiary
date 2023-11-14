import {useState} from "react";

export default function SessionForm() {

    const [form, setForm] = useState({
        skill: '',
        description: '',
        startPeriod: '',
        endPeriod: '',
        distraction: '',
        productivity: ''
    })

    function handleFormUpdate(e) {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    }

    return (
        <form>
            <label>
                Skill
                <input
                    type="text"
                    name="skill"
                    value = {form.skill}
                    onChange={handleFormUpdate}
                />
            </label>
            <label>
                Description
                <input
                    type="text"
                    name="description"
                    value = {form.description}
                    onChange={handleFormUpdate}
                />
            </label>

            <label>
                Start Period
                <input
                    type="datetime-local"
                    name="startPeriod"
                    value = {form.startPeriod}
                    onChange={handleFormUpdate}
                />
            </label>
            <label>
                End Period
                <input
                    type="datetime-local"
                    name="endPeriod"
                    value = {form.endPeriod}
                    onChange={handleFormUpdate}
                />
            </label>

            <label>
                Distraction
                <input
                    type="text"
                    name="distraction"
                    value = {form.distraction}
                    onChange={handleFormUpdate}
                />
            </label>
            <label>
                Productivity
                <input
                    type="number"
                    name="productivity"
                    value = {form.productivity}
                    onChange={handleFormUpdate}
                />
            </label>
        </form>
    )
}
