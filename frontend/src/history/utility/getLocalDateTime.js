export function getLocalDateTime(startDate, startTime) {

    const concatenatedDateTime = startDate.concat("T", startTime, "Z");
    return new Date(concatenatedDateTime);
}