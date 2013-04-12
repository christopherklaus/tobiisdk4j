package tests;

import tobii.APIException;
import tobii.Configuration;
import tobii.EyeTracker;
import tobii.GazeEvent;
import tobii.GazeListener;

public class SimpleClient {

	public static void main(String[] args) throws APIException, InterruptedException {
		final Configuration config = new Configuration();
		final EyeTracker tracker = new EyeTracker(config);		
		final GazeListener listener = new GazeListener() {
			
			@Override
			public void gazeEvent(GazeEvent event) {
				System.out.println(event.clientTimeElapsed);
			}
			
			@Override
			public void apiException(APIException exception) {
				System.out.println(exception);
			}
		};
		
		tracker.connect().register(listener).start();
		
		Thread.sleep(50000);
	}

}
