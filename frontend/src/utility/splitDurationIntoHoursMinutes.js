export function splitDurationIntoHoursMinutes(duration) {
    const hourAndMinute = duration / 60;
    const hours = Math.floor(hourAndMinute);
    const minutes = Math.round((hourAndMinute - hours) * 60);
    return {hours, minutes};
}