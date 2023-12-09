export function getDurationInMinutes(hours, minutes) {

    const parsedHours = parseInt(hours, 10) * 60;
    const parsedMinutes = parseInt(minutes, 10);
    return parsedHours + parsedMinutes;
}