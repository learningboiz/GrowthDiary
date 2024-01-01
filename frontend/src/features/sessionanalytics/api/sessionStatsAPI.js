const SESSION_ENDPOINT = 'http://localhost:8080/session/analytics?' //added '?' since its a query param
export default async function sessionStatsAPI() {
    try {
        const response = await fetch(SESSION_ENDPOINT)

        return await response;
    } catch (error) {
        console.error("Error", error);
        throw error;
    }
}