const HISTORY_ENDPOINT = 'http://localhost:8080/session/history';
export default async function defaultHistoryAPI() {
    try {
        return await fetch(HISTORY_ENDPOINT, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
    } catch (error) {
        console.error("Error", error);
        throw error;
    }
}