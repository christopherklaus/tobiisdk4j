package tobii;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

public class LiveTest {

	@Test
	public void testConnect() throws APIException {
		final EyeTracker tracker = new EyeTracker(null);		
		tracker.connect();
	}

	

	@Test
	public void testGazeData() throws APIException, InterruptedException {
		final EyeTracker tracker = new EyeTracker(null);		
		final AtomicReference<GazeEvent> ref = new AtomicReference<>();
		
		final GazeListener listener = new GazeListener() {
			
			@Override
			public void gazeEvent(GazeEvent event) {
				System.out.println(event.status);
				ref.set(event);
			}
			
			@Override
			public void apiException(APIException exception) {
				System.out.println(exception);
				assertTrue(false);
			}
		};
		
		tracker.connect().register(listener).start();
		
		Thread.sleep(500);
		
		tracker.stop().disconnect();
		
		assertNotNull(ref.get());		
	}
}
