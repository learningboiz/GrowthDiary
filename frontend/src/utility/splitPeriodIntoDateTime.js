export function splitPeriodIntoDateTime(timePeriod) {
    const parsedTimePeriod = timePeriod.split('T');
    const date = parsedTimePeriod[0];
    const time = parsedTimePeriod[1].replace('Z', '');
    return [date, time];
}