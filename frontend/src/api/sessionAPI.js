/**
 * Makes a POST request to the session API endpoint with the provided form data
 * Response and/or error is returned instead of being processed directly to ensure modularity/SRP 
 * The component utilising this method will be responsible for how the response data is used
 *
 * @param finalisedForm The formatted form data to be sent to the API
 * @returns {Promise<Response>} A promise that resolves to the response of the API request
 * @throws {Error} Throws an error if the API request fails
 */

const SESSION_ENDPOINT = 'http://localhost:8080/session';
export default async function sessionAPI(sessionData) {
    try {
        const response = await fetch(SESSION_ENDPOINT, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(sessionData)
        })

        return await response;
    } catch (error) {
        console.error("Error", error);
        throw error;
    }
}