const SESSION_ENDPOINT = 'http://localhost:8080/session/analytics/productivity?' //added '?' since its a query param
export default async function productivityChartAPI(chartCategory) {
    try {
        const response = await fetch(SESSION_ENDPOINT + new URLSearchParams({
            category: chartCategory
        }))

        return await response;
    } catch (error) {
        console.error("Error", error);
        throw error;
    }
}