export default function DetailsForm({detailsForm, handleFormUpdate}) {
    return (
        <form>
            <label>
                Skill
                <input
                    type="text"
                    name="skill"
                    value = {detailsForm.skill}
                    onChange={handleFormUpdate}
                />
            </label>
            <label>
                Description
                <input
                    type="text"
                    name="description"
                    value = {detailsForm.description}
                    onChange={handleFormUpdate}
                />
            </label>
        </form>
    )
}