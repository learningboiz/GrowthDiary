import { expect, test } from 'vitest'
import {getDurationInMinutes} from "../../features/sessiontracker/utility/getDurationInMinutes.js";

const testCases = [
    {
        hours: 1,
        minutes: 30,
        expectedDuration: 90
    },
    {
        hours: 0,
        minutes: 45,
        expectedDuration: 45
    },
    {
        hours: 3,
        minutes: 0,
        expectedDuration: 180
    },
    {
        hours: 2,
        minutes: 22,
        expectedDuration: 142
    },
    {
        hours: 0,
        minutes: 0,
        expectedDuration: 0
    },
];

function testEachCase(testCases) {

    let index = 0;

    testCases.forEach((testCase) => {
        index += 1;
        const { hours, minutes, expectedDuration } = testCase;
        const actualDuration = getDurationInMinutes(hours, minutes);

        testDurationParser(expectedDuration, actualDuration, index)
    })
}

function testDurationParser(expectedDuration, actualDuration, index) {
    test(`Test case ${index}: Duration calculator produces accurate total minutes`, () => {
        expect(actualDuration).toStrictEqual(expectedDuration);
    })
}

testEachCase(testCases)