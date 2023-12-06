import { expect, test } from 'vitest'
import {calculateDuration} from "../../tracker/utility/calculateDuration.js";

function testDurationCalculator(startPeriod, endPeriod, expectedDuration, actualDuration) {
    test(`Duration between ${startPeriod} and ${endPeriod} should be ${expectedDuration}`, () => {
        expect(actualDuration).toBe(expectedDuration)
    })
}


const startPeriod = new Date('December 15, 2023 09:30:00');
const endPeriod = new Date('December 15, 2023 10:00:00');
const expectedDuration = 30;
const actualDuration = calculateDuration(startPeriod, endPeriod);

testDurationCalculator(startPeriod, endPeriod, expectedDuration, actualDuration);