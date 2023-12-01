export default function sendSessionAPIRequest(finalisedForm) {
    fetch('http://localhost:8080/session', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(finalisedForm)
    })
        .then((response) => {
            return response.json();
        })
}