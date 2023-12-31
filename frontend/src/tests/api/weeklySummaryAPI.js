import weeklySummaryAPI from "../../features/sessionanalytics/api/weeklySummaryAPI.js";// Adjust the path accordingly
import { describe, test, expect } from 'vitest'

describe('weeklySummaryAPI', () => {
    test('fetches data correctly', async () => {
        // TODO this banks on data existing within the database, else it'll return null/undefined
        const userCurrentDate = '2023-12-01';

        const result = await weeklySummaryAPI(userCurrentDate);
        const json = await result.json();
        expect(result.status).toBe(202);
        expect(json).toBeDefined();
    });
});