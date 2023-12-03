export function splitHourMinute(duration) {
    const hourMin = duration / 60;
    const quotient = Math.floor(hourMin);
    const remainder = Math.round((hourMin - quotient) * 60);
    return [quotient, remainder];
}