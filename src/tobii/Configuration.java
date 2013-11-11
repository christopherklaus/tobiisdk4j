package tobii;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.Pointer.StringType;

import tobii.lowlevel.config.TobiiSDKConfigLibrary;
import tobii.lowlevel.sdk.TobiiSDKLibrary;

/**
 * The low level eye tracking configuration
 * 
 * @author Ralf Biedert <rb@xr.io>
 */
public class Configuration {
		
	private final long startTime;

	public Configuration() {	
		this.startTime = System.nanoTime();		
	}
	
	
	/**
	 * Initializes the configuration.
	 * @return
	 * @throws APIException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Configuration init() throws APIException {
		// Initialize the configuration
		final Pointer error = Pointer.allocateInt();
				
		try {
			TobiiSDKConfigLibrary.tobiigaze_config_init((Pointer<IntValuedEnum<TobiiSDKConfigLibrary.tobiigaze_error_code >>) error);		
			except(error.getInt());
		} finally {
			error.release();			
		}	
		
		return this;		
	}
	
	
	/**
	 * Returns the URL of the default tracking device. 
	 * 
	 * @return
	 * @throws APIException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })	
	public String defaultTrackerURL() throws APIException {
		final int URLSIZE = 128;
		final Pointer error = Pointer.allocateInt();
		final Pointer url = Pointer.allocateChars(URLSIZE);
		
		try {
			TobiiSDKConfigLibrary.tobiigaze_config_get_default_eye_tracker_url(url, URLSIZE, error);
			except(error.getInt());
			
			return url.getString(StringType.C);					
		} finally {
			error.release();
			url.release();		
		}	
	} 
	
	
	/**
	 * Checks the return value and throws an exception if there was an error.
	 * 
	 * @param code
	 * @throws APIException
	 */
	protected void except(int code) throws APIException {
		if (code == 0) return;
				
		throw new APIException(code, exceptionMessage(code));
	}
	
	protected APIException exception(int code) {
		if (code == 0) return null;		
		return new APIException(code, exceptionMessage(code));
	}
	
	protected static String exceptionMessage(int code) {
		return "TODO (" + code + ")";
	}
	
	/**
	 * Returns the version of the native API.
	 * 
	 * @return E.g., <code>2.0.0.108</code>
	 */
	public String APIVersion() {
		return TobiiSDKLibrary.tobiigaze_get_version().getCString();
	}	

	
	/**
	 * Returns the time elapsed since this object was created in milliseconds.
	 * 
	 * @return
	 */
	public long elapsed() {
		return (System.nanoTime() - startTime) / (1000 * 1000);
	}
	
	/**
	 * Opens the eye tracker system control panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public void controlPanel() throws APIException{
		final Pointer error = Pointer.allocateInt();
		
		try {
			TobiiSDKConfigLibrary.tobiigaze_config_launch_control_panel(error);
			except(error.getInt());
		} finally {
			error.release();
		}	
	}		
}
