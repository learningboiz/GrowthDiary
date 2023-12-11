import { expect, test } from 'vitest'
import {getDateAndTime} from "../../tracker/utility/getDateAndTime.js";

const testCases = [
    {
        startDate: '2023-12-01T23:40:34.241Z',
        expectedDate: '2023-12-01',
        expectedTime: '23:40:34.241'
    },
    {
        startDate: '2023-12-10T08:45:00.123Z',
        expectedDate: '2023-12-10',
        expectedTime: '08:45:00.123'
    },
    {
        startDate: '2023-12-01T00:00:00.000Z',
        expectedDate: '2023-12-01',
        expectedTime: '00:00:00.000'
    }
];

function testEachCase(testCases) {

    let index = 0;

    testCases.forEach((testCase) => {
        index += 1;
        const { startDate, expectedDate, expectedTime } = testCase;
        const actualDateTime = getDateAndTime(startDate);
        const expectedDateTime = [expectedDate, expectedTime];

        testDateConversion(actualDateTime, expectedDateTime, index)
    })
}

testEachCase(testCases)

function testDateConversion(actualDateTime, expectedDateTime, index) {
    test(`Test case ${index}: Date converter produces the accurate dates and times`, () => {
        expect(actualDateTime).toStrictEqual(expectedDateTime)
    })
}

