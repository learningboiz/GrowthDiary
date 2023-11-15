export function calculateDuration(startPeriod, endPeriod) {
    const startDate = new Date(startPeriod);
    const endDate = new Date(endPeriod);
    const differenceInMilliseconds = endDate - startDate;
    return Math.round(differenceInMilliseconds / 60000);
}