export default async function sendSessionAPIRequest(finalisedForm) {
    const response = await fetch('http://localhost:8080/session', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(finalisedForm)
    })

    return response.json();
}