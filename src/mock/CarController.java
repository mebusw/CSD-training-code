package mock;

public class CarController {

	public boolean getReadyToGo(Engine engine, Gearbox gearbox,
			Electronics electronics, StatusPanel statusPanel) {
		return true;
	}

	public void goForward(Electronics electronics, StatusPanel statusPanel) {
		if (statusPanel.engineIsRunning() && statusPanel.thereIsEnoughFuel())
			electronics.accelerate();
	}

	public void stop(int halfBrakingPower, Electronics electronics,
			StatusPanel statusPanel) {
		electronics.pushBrakes(halfBrakingPower);
		if (statusPanel.getSpeed() > 0) {
			this.stop(halfBrakingPower, electronics, statusPanel);
		}
	}

}
