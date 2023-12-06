export function calculateDuration(startPeriod, endPeriod) {
    return Math.round((endPeriod - startPeriod) / 60000);
}
