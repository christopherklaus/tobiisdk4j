package sandbox;

import tobii.APIException;
import tobii.GazeEvent;
import tobii.GazeListener;
import tobii.MouseTracker;
import tobii.Tracker;

public class MouseClient {
	public static void main(String[] args) throws APIException,
			InterruptedException {
		final Tracker tracker = new MouseTracker();
		final GazeListener listener = new GazeListener() {

			@Override
			public void gazeEvent(GazeEvent event) {
				System.out.println(event.left.gazeOnDisplayNorm);
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
