export function getFormattedDateTime(localDateTime) {

    const sessionDate = localDateTime.toLocaleDateString('en-GB', {
        day: '2-digit',
        month: 'short',
        year: 'numeric'
    });
    const sessionTime = localDateTime.toLocaleTimeString('en', {
        hour: 'numeric',
        minute: 'numeric',
        hour12: true
    });

    return {sessionDate, sessionTime};
}