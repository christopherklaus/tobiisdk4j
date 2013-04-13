package tobii;

import java.io.Serializable;

import tobii.lowlevel.sdk.tobiigaze_gaze_data_eye;
import tobii.lowlevel.sdk.tobiigaze_point_2d;
import tobii.lowlevel.sdk.tobiigaze_point_3d;
import tobii.util.V2;
import tobii.util.V3;

/**
 * Represents the measurement of a single eye.
 * 
 * @author Ralf Biedert <rb@xr.io>
 */
public class GazeEventEyeInfo implements Serializable {

	protected GazeEventEyeInfo(V3 eyePosFromTrackerMM, V3 eyePosInBoxNorm, V3 gazeFromTrackerMM, V2 gazeOnDisplayNorm ) {
		this.eyePosFromTrackerMM = eyePosFromTrackerMM;
		this.eyePosInBoxNorm = eyePosInBoxNorm;
		this.gazeFromTrackerMM = gazeFromTrackerMM;
		this.gazeOnDisplayNorm = gazeOnDisplayNorm;
	}
	
	/** */
	private static final long serialVersionUID = 7837497048744766059L;
	
	/** describes the position of the eyeball in 3D space */
	public final V3 eyePosFromTrackerMM;

	/** gives the position of the eyeball within the track box volume */
	public final V3 eyePosInBoxNorm;

	/** point on the calibration plane where the user's gaze is */
	public final V3 gazeFromTrackerMM;

	/** expressed as a two-dimensional point on the calibration plane instead of as a point in 3D space. */
	public final V2 gazeOnDisplayNorm;
		
	protected static V3 v3(tobiigaze_point_3d p) {
		return new V3(p.x(), p.y(), p.z());
	}
	
	private static V2 v2(tobiigaze_point_2d p) {
		return new V2(p.x(), p.y());		
	}

	protected static GazeEventEyeInfo create(tobiigaze_gaze_data_eye data) {				
		return new GazeEventEyeInfo(v3(data.eye_position_from_eye_tracker_mm()),
				v3(data.eye_position_in_track_box_normalized()),
				v3(data.gaze_point_from_eye_tracker_mm()),
				v2(data.gaze_point_on_display_normalized()));
	}
}
