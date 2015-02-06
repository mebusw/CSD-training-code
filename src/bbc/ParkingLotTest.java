package bbc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParkingLotTest {

	private ParkingLot pl;

	@Before
	public void setUp() throws Exception {
		System.out.println("Before");
		// given
		pl = new ParkingLot();

	}


	@Test
	public void simpleAdd() {
		System.out.println("simpleAdd");

		// when
		boolean result = pl.put();

		// then
		assertTrue(result);
		assertEquals(2, 1 + 2 - 1);
	}

	@Test
	public void simpleMinus() {
		System.out.println("simpleMinus");

		assertEquals(2, 3 - 1);
	}

}
