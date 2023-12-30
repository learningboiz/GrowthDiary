export default function getDateDaysAgo(currentDate, daysAgo) {
    const dateDaysAgo = new Date(currentDate);
    dateDaysAgo.setDate(dateDaysAgo.getDate() - daysAgo);
    return dateDaysAgo;
}