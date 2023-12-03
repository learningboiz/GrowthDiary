import { expect, test } from 'vitest'
import { splitHourMinute } from "../../tracker/utility/splitHourMinute.js";

function testDurationConversion(duration, expectedHours, expectedMinutes, actualHours, actualMinutes) {
    test(`${duration} minutes is split into ${expectedHours} hour(s) and ${expectedMinutes} minute(s)`, () => {
        expect(actualHours).toBe(expectedHours)
        expect(actualMinutes).toBe(expectedMinutes)
    })
}

const durationA = 90;
const [hoursA, minutesA] = splitHourMinute(durationA);
testDurationConversion(durationA, 1, 30, hoursA, minutesA);

const durationB = 88;
const [hoursB, minutesB] = splitHourMinute(durationB);
testDurationConversion(durationB, 1, 28, hoursB, minutesB);

const durationC = 199;
const [hoursC, minutesC] = splitHourMinute(durationC);
testDurationConversion(durationC, 3, 19, hoursC, minutesC);