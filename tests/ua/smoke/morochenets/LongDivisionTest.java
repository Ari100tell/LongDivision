package ua.smoke.morochenets;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Ari100tell
 * Date: 24.05.13
 * Time: 2:10
 */


public class LongDivisionTest {
    LongDivision longDivision = new LongDivision();

    @org.junit.Test
    public void testDivisionInsideColumn() throws Exception {
        assertEquals(22, longDivision.divisionInsideColumn(162, 7));
        assertEquals(-22, longDivision.divisionInsideColumn(-162, 7));

    }

    @org.junit.Test
    public void testCountingNumberDigits() throws Exception {
        assertEquals(3, longDivision.countingNumberDigits(234));
        assertEquals(3, longDivision.countingNumberDigits(-234));
        assertEquals(1, longDivision.countingNumberDigits(0));
    }

    @org.junit.Test
    public void testExponentiationWithBaseTen() throws Exception {
        assertEquals(1000, longDivision.exponentiationWithBaseTen(3));
        assertEquals(0, longDivision.exponentiationWithBaseTen(-3));
    }

    @org.junit.Test
    public void testTrimmingEndOfIntegers() throws Exception {
        assertEquals(4, longDivision.trimmingEndOfIntegers(4443, 3));
        assertEquals(-4, longDivision.trimmingEndOfIntegers(-4443, 3));
        assertEquals(0, longDivision.trimmingEndOfIntegers(0, 6));
        assertEquals(5, longDivision.trimmingEndOfIntegers(5, 0));
    }

}
