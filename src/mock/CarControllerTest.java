package mock;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class CarControllerTest {

	@Test
	public void testItCanGetReadyTheCar() {
		CarController carController = new CarController();

		Engine engine = new Engine();
		Gearbox gearbox = new Gearbox();
		Electronics electronics = new Electronics();
		StatusPanel statusPanel = new StatusPanel();

		boolean result = carController.getReadyToGo(engine, gearbox,
				electronics, statusPanel);

		assertTrue(result);
	}

	@Test
	public void testItCanAccelerate() {
		CarController carController = new CarController();
		Electronics mockElectronics = mock(Electronics.class);
		StatusPanel mockStatusPanel = mock(StatusPanel.class);
		when(mockStatusPanel.thereIsEnoughFuel()).thenReturn(true);
		when(mockStatusPanel.engineIsRunning()).thenReturn(true);

		carController.goForward(mockElectronics, mockStatusPanel);

		verify(mockStatusPanel, times(1)).thereIsEnoughFuel();
		verify(mockStatusPanel, times(1)).engineIsRunning();
		verify(mockElectronics, times(1)).accelerate();

	}

	@Test
	public void testItCanStop() {
		CarController carController = spy(new CarController());
		int halfBrakingPower = 50;
		Electronics mockElectronics = mock(Electronics.class);
		StatusPanel mockStatusPanel = mock(StatusPanel.class);
		when(mockStatusPanel.getSpeed()).thenReturn(1).thenReturn(0);

		carController.stop(halfBrakingPower, mockElectronics, mockStatusPanel);

		verify(mockElectronics, times(2)).pushBrakes(halfBrakingPower);
		verify(mockStatusPanel, times(2)).getSpeed();
        verify(carController, times(2)).stop(anyInt(), any(Electronics.class), any(StatusPanel.class));

	}

}
