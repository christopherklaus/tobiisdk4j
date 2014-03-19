package tobii;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.Pointer.StringType;

import tobii.lowlevel.sdk.TobiiSDKLibrary;
import tobii.lowlevel.sdk.TobiiSDKLibrary.tobiigaze_async_basic_callback;
import tobii.lowlevel.sdk.TobiiSDKLibrary.tobiigaze_async_callback;
import tobii.lowlevel.sdk.TobiiSDKLibrary.tobiigaze_error_code;
import tobii.lowlevel.sdk.TobiiSDKLibrary.tobiigaze_eye_tracker;
import tobii.lowlevel.sdk.TobiiSDKLibrary.tobiigaze_gaze_listener;
import tobii.lowlevel.sdk.TobiiSDKLibrary.tobiigaze_key_provider_callback;
import tobii.lowlevel.sdk.tobiigaze_gaze_data;
import tobii.lowlevel.sdk.tobiigaze_gaze_data_extensions;
import tobii.lowlevel.sdk.tobiigaze_key;

/**
 * Represents a true eye tracking device.
 * 
 * @author Ralf Biedert <rb@xr.io>
 */
public final class EyeTracker extends AbstractTracker {
	private final String url;
	
	/** The actual eye tracker object */ 
	private Pointer<tobiigaze_eye_tracker> tracker;	
	
	/** Worker thread */ 
	private Thread thread;	
	
	/** Our callbacks ... */
	private GazeCallback callback;
	private KeyProviderCallback keycallback;

	/** Key to transmit */
	private String key;
	
	
	protected class KeyProviderCallback extends tobiigaze_key_provider_callback {
		@Override
		public void apply(int realm_id, Pointer<tobiigaze_key> ptr, Pointer<?> user_data) {
			ptr.setString(key, StringType.C);			
		}
	}	

	protected abstract class ErrorCallback extends tobiigaze_async_callback {

	}	

	
	/**
	 * Internally used callback for gaze.
	 * 
	 * @author Ralf Biedert <rb@xr.io>
	 */
	protected class GazeCallback extends tobiigaze_gaze_listener {		
		
		@Override
		public void apply(Pointer<tobiigaze_gaze_data > gaze_data, Pointer<tobiigaze_gaze_data_extensions > gaze_data_extensions, Pointer<? > user_data) {
			
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
	 * Just creates an eye tracker object with the default URL.
	 * 
	 * @throws APIException
	 */
	public EyeTracker() throws APIException {
		this(null);
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
	public EyeTracker(String url) throws APIException {
		this.url = url; 
	}
	
	/***
	 * Sets the cheat code to use ...
	 * 
	 * @param realm
	 * @param key
	 * @return
	 */
	public EyeTracker cheatcode(String key) {
		this.key = key;		
		return this;
	}
	
	
	/**
	 * Returns an appropriate exception.
	 * 
	 * @param message
	 * @return
	 */
	protected APIException exception(int message) {
		if (message == 0) return null;
		return new APIException(message, "Tobii Exception " + message);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override	
	public EyeTracker connect() throws APIException {
		final Pointer error = Pointer.allocateInt();
		final Pointer url = Pointer.allocateChars(1000);		
		
		error.setInt(0);
		
		try {
			// Set the actual URL string we got previously
			if (this.url == null || "".equals(this.url)) {
				TobiiSDKLibrary.tobiigaze_get_connected_eye_tracker(url, 1000, error);
			} else {
				url.setString(this.url, StringType.C);	
			}			

			// If there was an error getting the tracker, throw exception.
			if (error.getInt() > 0) throw exception(error.getInt());
					
			// Create the tracker object
			this.tracker = TobiiSDKLibrary.tobiigaze_create((Pointer<Byte>) url, error);
			this.thread = new Thread(new Runnable() {			
				@Override
				public void run() {
					final Pointer error = Pointer.allocateInt();
					try {
						TobiiSDKLibrary.tobiigaze_run_event_loop(tracker, error);
						
						// Check if there was an exception we should report
						final APIException exception = exception(error.getInt());
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
			
			TobiiSDKLibrary.tobiigaze_register_error_callback(tracker, (new ErrorCallback() {				
				@Override
				public void apply(int error_code, Pointer<?> user_data) {
					synchronized (listener) {
						APIException e = exception(error_code);
						for (GazeListener l : listener) {
							l.apiException(e);
						}
					}			
				}
			}.toPointer()), null);
			
			// Event loop must run in background			
			this.thread.setDaemon(true);
			this.thread.start();
			
			// Only register key provider if we have set a key.
			if (this.key != null) {
				this.keycallback = new KeyProviderCallback();
				TobiiSDKLibrary.tobiigaze_register_key_provider(tracker, (Pointer<TobiiSDKLibrary.tobiigaze_key_provider_callback>) this.keycallback.toPointer(), error, url);
				if (error.getInt() > 0) throw exception(error.getInt()); 				
			}
									
			// Actually connect to the device			
			TobiiSDKLibrary.tobiigaze_connect(tracker, error);			
			if (error.getInt() > 0) throw exception(error.getInt());
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
			exception(error.getInt()); 				
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
			exception(error.getInt()); 				
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


	@Override
	public String url() {
		return this.url;
	}
}
