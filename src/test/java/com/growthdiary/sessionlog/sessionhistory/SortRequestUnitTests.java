package com.growthdiary.sessionlog.sessionhistory;

import com.growthdiary.sessionlog.history.requests.SortRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SortRequestUnitTests {

    @Test
    public void testSortCreation() {
        SortRequest request = new SortRequest.Builder()
                .sortByDuration()
                .build();

        assertNotNull(request.getSort());
    }

    @Test
    public void testMultipleSorts() {

        // Each request should only have one sorting criteria
        assertThrows(UnsupportedOperationException.class, () -> {
                SortRequest request = new SortRequest.Builder()
                .sortByDuration()
                .sortByDate()
                .sortByDate()
                .ascending()
                .build();
        });
    }

    @Test
    public void testEmptyRequest() {
        assertThrows(UnsupportedOperationException.class, () -> {
            SortRequest request = new SortRequest.Builder().build();
        });
    }
}
