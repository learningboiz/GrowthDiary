export default function FeedbackForm({feedbackForm, handleFormUpdate}) {
    return (
        <form>
            <label>
                Distraction
                <input
                    type="text"
                    name="distraction"
                    value = {feedbackForm.distraction}
                    onChange={handleFormUpdate}
                />
            </label>
            <label>
                Productivity
                <input
                    type="number"
                    name="productivity"
                    value = {feedbackForm.productivity}
                    onChange={handleFormUpdate}
                />
            </label>
        </form>
    )
}