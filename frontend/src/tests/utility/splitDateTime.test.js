import { expect, test } from 'vitest'
import { splitDateTime } from "../../tracker/utility/splitDateTime.js";

function testDateConversion(timePeriod, expectedDate, expectedTime, actualDate, actualTime) {
    test(`${timePeriod} minutes is split into ${expectedDate} and ${expectedTime}`, () => {
        expect(actualDate).toBe(expectedDate)
        expect(actualTime).toBe(expectedTime)
    })
}

const timePeriodA = '2023-12-01T23:40:34.241Z';
const [dateA, timeA] = splitDateTime(timePeriodA);
testDateConversion(timePeriodA, '2023-12-01', '23:40:34.241', dateA, timeA);

const timePeriodB = '2023-12-01T00:30:28.241Z';
const [dateB, timeB] = splitDateTime(timePeriodB);
testDateConversion(timePeriodB, '2023-12-01', '00:30:28.241', dateB, timeB);