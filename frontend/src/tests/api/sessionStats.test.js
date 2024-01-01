import sessionStatsAPI from "../../features/sessionanalytics/api/sessionStatsAPI.js";// Adjust the path accordingly
import { describe, test, expect } from 'vitest'

describe('sessionStatsAPI', () => {
    test('fetches data correctly', async () => {
        // TODO this banks on data existing within the database, else it'll return null/undefined

        const result = await sessionStatsAPI();
        const json = await result.json();
        expect(result.status).toBe(202);
        expect(json).toBeDefined();
    });
});