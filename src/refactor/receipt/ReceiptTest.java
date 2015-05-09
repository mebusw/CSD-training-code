package refactor.receipt;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.ArrayList;

public class ReceiptTest {

    @Test
    public void testCalculateGrandTotal() {
        Receipt receipt = new Receipt(
            new ArrayList<Double>(Arrays.asList(1.0, 2.0)), 
            new ArrayList<Double>(Arrays.asList(100.0, 200.0, 300.0)));

        assertEquals(635.805, receipt.CalculateGrandTotal(), 0.000001);
    }

    @Test
    public void testCalculateGrandTotalWithoutDiscounts() {
        Receipt receipt = new Receipt(
            new ArrayList<Double>(),
            new ArrayList<Double>(Arrays.asList(100.0, 200.0, 300.0)));

        assertEquals(639.0, receipt.CalculateGrandTotal(), 0.000001);
    }

    @Test
    public void testCalculateGrandTotalWithoutItems() {
        Receipt receipt = new Receipt(
            new ArrayList<Double>(),
            new ArrayList<Double>());

        assertEquals(0.0, receipt.CalculateGrandTotal(), 0.000001);
    }
}