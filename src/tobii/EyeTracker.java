package tobii;

import org.bridj.Pointer;
import org.bridj.Pointer.StringType;

import tobii.lowlevel.sdk.TobiiSDKLibrary;
import tobii.lowlevel.sdk.TobiiSDKLibrary.tobiigaze_eye_tracker;
import tobii.lowlevel.sdk.TobiiSDKLibrary.tobiigaze_gaze_listener;
import tobii.lowlevel.sdk.tobiigaze_gaze_data;

/**
 * Represents a true eye tracking device.
 * 
 * @author Ralf Biedert <rb@xr.io>
 */
public final class EyeTracker extends AbstractTracker {

	private final Configuration configuration;	
	private final String url;
	
	/** The actual eye tracker object */ 
	private Pointer<tobiigaze_eye_tracker> tracker;
	
	/** Worker thread */ 
	private Thread thread;	
	
	/** I'll call back ... */
	private GazeCallback callback;
	
	
	/**
	 * Internally used callback for gaze.
	 * 
	 * @author Ralf Biedert <rb@xr.io>
	 */
	protected class GazeCallback extends tobiigaze_gaze_listener {		
		
		@Override
		public void apply(Pointer<tobiigaze_gaze_data> gaze_data,
				Pointer<?> user_data) {
			
			final tobiigaze_gaze_data data = gaze_data.apply(0);
						
			final GazeEvent e = new GazeEvent(
					System.nanoTime(), 
					data.timestamp(),
					(int) data.tracking_status().value(),
					GazeEventEyeInfo.create(data.left()),
					GazeEventEyeInfo.create(data.right()));
			
			dispatchGazeEvent(e);
		}
	}	
	
	/**
	 * Creates a new eye tracker connection for the given URL. 
	 * 
	 * @param configuration
	 * 
	 * @throws APIException
	 */
	public EyeTracker(Configuration configuration) throws APIException {
		this(configuration, null);
	}



	/**
	 * Creates a new eye tracker connection for the given URL. 
	 * 
	 * @param configuration
	 * @param url
	 * 
	 * @throws APIException
	 * 
	 */
	public EyeTracker(Configuration configuration, String url) throws APIException {
		this.configuration = configuration;			
		this.url = url == null || "".equals(url) ? configuration.defaultTrackerURL() : url; 
	}
		
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override	
	public EyeTracker connect() throws APIException {
		final Pointer error = Pointer.allocateInt();
		final Pointer url = Pointer.allocateChars(1000);
		
		try {
			// Set the actual URL string we got previously
			url.setString(this.url, StringType.C);
			
			// Create the tracker object
			this.tracker = TobiiSDKLibrary.tobiigaze_create((Pointer<Byte>) url);			
			this.thread = new Thread(new Runnable() {			
				@Override
				public void run() {
					final Pointer error = Pointer.allocateInt();
					try {
						TobiiSDKLibrary.tobiigaze_run_event_loop(tracker, error);
						
						// Check if there was an exception we should report
						final APIException exception = configuration.exception(error.getInt());
						if(exception != null) {
							synchronized (listener) {
								for (GazeListener l : listener) {
									l.apiException(exception);
								}
							}
						}
					} finally {
						error.release();
					}
				}
			});
			
			// Event loop must run in background			
			this.thread.setDaemon(true);
			this.thread.start();		
			
			
			// Actually connect to the device
			TobiiSDKLibrary.tobiigaze_connect(tracker, error);
			configuration.except(error.getInt()); 
		} finally {
			url.release();
			error.release();			
		}
		
		return this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Tracker start() throws APIException {
		// Check we are not already connected
		if (this.callback != null) return this;
		
		this.callback = new GazeCallback();

		final Pointer error = Pointer.allocateInt();
				
		try {
			TobiiSDKLibrary.tobiigaze_start_tracking(this.tracker, (Pointer<TobiiSDKLibrary.tobiigaze_gaze_listener>) callback.toPointer(), error, null);

			configuration.except(error.getInt()); 
		} catch (APIException e) {
			this.callback = null;			
		} finally {
			error.release();			
		}
		
		return this;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Tracker stop() throws APIException {
		// Check we are already connected
		if (this.callback == null) return this;
		
		final Pointer error = Pointer.allocateInt();
				
		try {
			TobiiSDKLibrary.tobiigaze_stop_tracking(this.tracker, error);

			configuration.except(error.getInt()); 
		} finally {
			error.release();			
		}
		
		this.callback = null;			
	
		return this;
	}


	
	@Override	
	public EyeTracker disconnect() throws APIException {
		if (this.tracker == null) return this;
				
		TobiiSDKLibrary.tobiigaze_disconnect(this.tracker);
		TobiiSDKLibrary.tobiigaze_destroy(this.tracker);
		
		this.thread = null;
		this.callback = null;
		
		return this;
	}
}
