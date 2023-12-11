const HISTORY_ENDPOINT = 'http://localhost:8080/session/history';
export default async function sessionAPI() {
    try {
        const response = await fetch(HISTORY_ENDPOINT, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })

        return await response;
    } catch (error) {
        console.error("Error", error);
        throw error;
    }
}