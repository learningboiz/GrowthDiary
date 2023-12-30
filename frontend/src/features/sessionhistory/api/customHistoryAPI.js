const SESSION_ENDPOINT = 'http://localhost:8080/session/history/search'
export default async function customHistoryAPI(customHistory) {
    try {
        const response = await fetch(SESSION_ENDPOINT, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(customHistory)
        })

        return await response;
    } catch (error) {
        console.error("Error", error);
        throw error;
    }
}