package tobii;

import java.io.Serializable;

/**
 * A single gaze measurement from the eye tracker.
 * 
 * @author Ralf Biedert <rb@xr.io>
 */
public class GazeEvent implements Serializable {
	/** */
	private static final long serialVersionUID = -8792167654827535512L;

	
	public GazeEvent(long nanoTimeReceived, long eventTime, int status, GazeEventEyeInfo left, GazeEventEyeInfo right) {
		this.nanoTimeReceived = nanoTimeReceived;
		this.eventTime = eventTime;		 
		this.status = status;
		this.left = left;
		this.right = right;		
	}
	
	/** The timestamp on the Java side (System.nano() high precision time) when we received it. */
	public final long nanoTimeReceived;
	
	/** The internal timestamp on the Tobii / native Win32 side when this was recorded */
	public final long eventTime;
	
	/** The tracking status code (0 == no eyes, 1 == both eyes, rest: see API) */
	public final int status;
	
	/** Eye info for the user's left eye */
	public final GazeEventEyeInfo left;
	
	/** Eye info for the user's right eye */
	public final GazeEventEyeInfo right;	
}
