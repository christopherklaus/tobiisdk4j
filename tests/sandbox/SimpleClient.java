package sandbox;

import tobii.APIException;
import tobii.Configuration;
import tobii.EyeTracker;
import tobii.GazeEvent;
import tobii.GazeListener;

public class SimpleClient {

	public static void main(String[] args) throws APIException, InterruptedException {
		final Configuration config = new Configuration().init();
		final EyeTracker tracker = new EyeTracker(config);		
		final GazeListener listener = new GazeListener() {
			
			@Override
			public void gazeEvent(GazeEvent event) {
				System.out.println(event.left.gazeOnDisplayNorm); // -1, -1 
				System.out.println(event.left.eyePosFromTrackerMM); // 0, 0, 0
				System.out.println(event.center().eyePosInBoxNorm); // -1, -1, 0
				System.out.println(event.left.gazeFromTrackerMM); // 0, 0, 0
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
