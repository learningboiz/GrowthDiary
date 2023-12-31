import productivityChartAPI from "../../features/sessionanalytics/api/productivityChartAPI.js";
import { describe, test, expect } from 'vitest'

describe('productivityChartAPI', () => {
    test('fetches data correctly', async () => {
        // TODO this banks on data existing within the database, else it'll return null/undefined
        const selectedCategory = "obstacle";

        const result = await productivityChartAPI(selectedCategory);
        const json = await result.json();
        expect(result.status).toBe(202);
        expect(json).toBeDefined();
    });
});