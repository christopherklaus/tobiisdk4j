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
	
	/*		
		TOBIIGAZE_TRACKING_STATUS_NO_EYES_TRACKED(0),
		TOBIIGAZE_TRACKING_STATUS_BOTH_EYES_TRACKED(1),
		TOBIIGAZE_TRACKING_STATUS_ONLY_LEFT_EYE_TRACKED(2),
		TOBIIGAZE_TRACKING_STATUS_ONE_EYE_TRACKED_PROBABLY_LEFT(3),
		TOBIIGAZE_TRACKING_STATUS_ONE_EYE_TRACKED_UNKNOWN_WHICH(4),
		TOBIIGAZE_TRACKING_STATUS_ONE_EYE_TRACKED_PROBABLY_RIGHT(5),
		TOBIIGAZE_TRACKING_STATUS_ONLY_RIGHT_EYE_TRACKED(6);
	 */		

	/** The tracking status code (0 == no eyes, 1 == both eyes, rest: see above) */
	public final int status;
	
	/** Eye info for the user's left eye */
	public final GazeEventEyeInfo left;
	
	/** Eye info for the user's right eye */
	public final GazeEventEyeInfo right;	
		
	/**
	 * Returns the best guess for the combined (a.k.a "center") gaze position.
	 * 
	 * @return The center gaze position based on both or one eye.
	 */
	public final GazeEventEyeInfo center() {		
		GazeEventEyeInfo a = status == 1 || status == 2 || status == 3 ? left : right;
		GazeEventEyeInfo b = status == 1 || status == 6 || status == 5 ? right : left;
		
		// In case both are invalid, return some invalid for center
		if (status == 0 || status == 4) {
			return b;
		}
		
		// Return an averaged result
		return new GazeEventEyeInfo(
				a.eyePosFromTrackerMM.add(b.eyePosFromTrackerMM).mul(0.5).v3(), 
				a.eyePosInBoxNorm.add(b.eyePosInBoxNorm).mul(0.5).v3(), 
				a.gazeFromTrackerMM.add(b.gazeFromTrackerMM).mul(0.5).v3(), 
				a.gazeOnDisplayNorm.add(b.gazeOnDisplayNorm).mul(0.5).v2());
	}
	
	public double gazedelta() {
		// Only compute this of both eyes are visible
		if (status == 1)
			return left.gazeOnDisplayNorm.dist(right.gazeOnDisplayNorm);
		
		// Otherwise the delta will be based on one eye only.
		else return 0;
	}
}
