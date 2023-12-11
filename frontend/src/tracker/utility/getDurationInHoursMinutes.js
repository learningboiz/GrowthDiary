export function getDurationInHoursMinutes(startPeriod, endPeriod) {
    const durationInMin = Math.round((endPeriod - startPeriod) / 60000);
    const hourAndMinute = durationInMin / 60;
    const hours = Math.floor(hourAndMinute);
    const minutes = Math.round((hourAndMinute - hours) * 60);
    return [hours, minutes];
}
