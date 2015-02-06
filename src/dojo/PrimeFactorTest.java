package dojo;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by jacky on 14-11-12.
 */
public class PrimeFactorTest {

    @Test
    public void primeFactors() {
        assertEquals(Arrays.asList(2), PrimeFactor.factors(2));
        assertEquals(Arrays.asList(3), PrimeFactor.factors(3));
        assertEquals(Arrays.asList(2,2), PrimeFactor.factors(4));
        assertEquals(Arrays.asList(5), PrimeFactor.factors(5));
        assertEquals(Arrays.asList(2,3), PrimeFactor.factors(6));
        assertEquals(Arrays.asList(7), PrimeFactor.factors(7));
        assertEquals(Arrays.asList(2,2,2), PrimeFactor.factors(8));
        assertEquals(Arrays.asList(3, 3), PrimeFactor.factors(9));
        assertEquals(Arrays.asList(7, 11, 19), PrimeFactor.factors(7*11*19));
    }
}
