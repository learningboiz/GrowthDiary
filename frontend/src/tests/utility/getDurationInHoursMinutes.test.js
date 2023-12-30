import { expect, test } from 'vitest'
import {getDurationInHoursMinutes} from "../../features/sessiontracker/utility/getDurationInHoursMinutes.js";

const testCases = [
    {
        startPeriod: new Date('December 15, 2023 10:30:00'),
        endPeriod: new Date('December 15, 2023 11:45:00'),
        expectedDuration: [1, 15],
    },
    {
        startPeriod: new Date('December 15, 2023 10:00:00'),
        endPeriod: new Date('December 15, 2023 11:00:00'),
        expectedDuration: [1, 0],
    },
    {
        startPeriod: new Date('December 15, 2023 12:00:00'),
        endPeriod: new Date('December 15, 2023 12:30:00'),
        expectedDuration: [0, 30],
    },
    {
        startPeriod: new Date('December 15, 2023 23:00:00'),
        endPeriod: new Date('December 16, 2023 00:30:00'),
        expectedDuration: [1, 30],
    },
    {
        startPeriod: new Date('December 15, 2023 10:30:00'),
        endPeriod: new Date('December 15, 2023 10:30:00'),
        expectedDuration: [0, 0],
    },
];

function testEachCase(testCases) {

    let index = 0;

    testCases.forEach((testCase) => {
        index += 1;
        const { startPeriod, endPeriod, expectedDuration } = testCase;
        const actualDuration = getDurationInHoursMinutes(startPeriod, endPeriod);

        testDurationParser(expectedDuration, actualDuration, index)
    })
}

function testDurationParser(expectedDuration, actualDuration, index) {
    test(`Test case ${index}: Duration calculator produces accurate hours and minutes`, () => {
        expect(actualDuration).toStrictEqual(expectedDuration);
    })
}

testEachCase(testCases)