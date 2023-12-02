export function splitDateTime(timePeriod) {
    const splitTimePeriod = timePeriod.split('T');
    const startDate = splitTimePeriod[0];
    const startTime = splitTimePeriod[1].replace('Z', '');
    return [startDate, startTime];
}