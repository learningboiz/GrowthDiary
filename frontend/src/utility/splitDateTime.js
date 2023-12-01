export function splitDateTime(timePeriod) {
    const splitTimePeriod = timePeriod.split('T');
    return {
        startDate: splitTimePeriod[0],
        startTime: splitTimePeriod[1].replace('Z', '')
    }
}