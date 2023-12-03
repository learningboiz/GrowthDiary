export default function SubmissionError({errorMessage}) {
    return (
        <>
            <h2>Ooopsies! Something went wrong.</h2>
            <p>{errorMessage}</p>
            <p>We have your data at hand. Try again later!</p>
        </>
    )
}