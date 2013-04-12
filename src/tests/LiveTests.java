package tests;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicReference;

import junit.framework.Assert;

import org.junit.Test;

import tobii.Configuration;
import tobii.EyeTracker;
import tobii.APIException;
import tobii.GazeEvent;
import tobii.GazeListener;

public class LiveTests {

	@Test
	public void testConnect() throws APIException {
		final Configuration config = new Configuration();
		final EyeTracker tracker = new EyeTracker(config);
		
		tracker.connect();
	}

	@Test
	public void testDeviceAddress() throws APIException {
		final Configuration config = new Configuration();
		Assert.assertNotNull(config.defaultTrackerURL());
		Assert.assertTrue(config.defaultTrackerURL().contains("tet-tcp://"));
	}
	

	@Test
	public void testGazeData() throws APIException, InterruptedException {
		final Configuration config = new Configuration();
		final EyeTracker tracker = new EyeTracker(config);		
		final AtomicReference<GazeEvent> ref = new AtomicReference<>();
		
		final GazeListener listener = new GazeListener() {
			
			@Override
			public void gazeEvent(GazeEvent event) {
				ref.set(event);
			}
			
			@Override
			public void apiException(APIException exception) {
				System.out.println(exception);
				Assert.assertTrue(false);
			}
		};
		
		tracker.connect().register(listener).start();
		
		Thread.sleep(500);
		
		Assert.assertNotNull(ref.get());		
	}
}
