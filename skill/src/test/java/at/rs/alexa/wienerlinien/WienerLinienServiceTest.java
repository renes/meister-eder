package at.rs.alexa.wienerlinien;


import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WienerLinienServiceTest {

    private WienerLinienService wienerLinienService = new WienerLinienService();

    @Test
    @Ignore
    public void testGetNextDepartures() {
        assertTrue(wienerLinienService.getNextDepartures().size() == 2);
        assertTrue(wienerLinienService.getNextDepartures().get(0) > 0);
        assertTrue(wienerLinienService.getNextDepartures().get(1) > 0);
    }
}