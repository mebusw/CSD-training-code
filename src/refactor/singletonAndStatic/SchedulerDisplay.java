package refactor.singletonAndStatic;

public class SchedulerDisplay {
	public void showEvent(Event event) {
		for(int n = 0; n < 1000; n++) {
			System.out.println("[" + event.getDate() + "]");	
		}
	}
}
