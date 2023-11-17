import { expect, test } from "vitest";
import { calculateDuration } from '../utility/calculateDuration.js';

function testCalculateDuration(startPeriod, endPeriod, expectedDuration, testSpecifics) {
    const actualDuration = calculateDuration(startPeriod, endPeriod);

    test('calculateDuration returns the correct ' + testSpecifics + ' duration', () => {
        expect(actualDuration).toBe(expectedDuration);
    } )
}

testCalculateDuration("2023-10-05T09:30:00Z","2023-10-05T10:00:00Z", 30, "even");
testCalculateDuration("2023-10-05T10:27:00Z","2023-10-05T10:36:00Z", 9, "odd");
testCalculateDuration("2023-10-05T23:30:00Z","2023-10-06T00:30:00Z", 60, "overnight");
