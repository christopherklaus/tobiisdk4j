package tobii;

/**
 * Implement this interface to receive gaze data and problems. 
 * 
 * @author Ralf Biedert <rb@xr.io>
 *
 */
public interface GazeListener {
	/**
	 * Called with a new gaze event.
	 * 
	 * @param event
	 */
	public void gazeEvent(GazeEvent event);
	
	/**
	 * Called when an exception occurred during recording.
	 * 
	 * @param exception
	 */
	public void apiException(APIException exception);	
}
