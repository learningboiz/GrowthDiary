/*
 * Parses a time period into an array of date and time
 * Useful within JS since initializing new Date() produces a period rather than a specific date
 */
export function splitPeriodIntoDateTime(timePeriod) {
    // TODO might want to handle the iso string change in this util


    const parsedTimePeriod = timePeriod.split('T');
    const date = parsedTimePeriod[0];
    const time = parsedTimePeriod[1].replace('Z', '');
    return [date, time];
}