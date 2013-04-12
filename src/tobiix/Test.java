package tobiix;
import java.io.File;

import tobii.lowlevel.API;
import tobii.lowlevel.SWIGTYPE_p_tobiigaze_error_code;
import tobii.lowlevel.SWIGTYPE_p_tobiigaze_eye_tracker;
import tobii.lowlevel.tobiigaze_error_code;
import tobii.lowlevel.tobiigaze_error_codep;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.load(new File("lib/tobii.sdk.dll").getAbsolutePath());

		SWIGTYPE_p_tobiigaze_eye_tracker eye_tracker = API.tobiigaze_create("tet-tcp://rexdl-010103000713.local./");
		
		
		tobiigaze_error_codep ep = new tobiigaze_error_codep();
		
		API.tobiigaze_connect(eye_tracker, ep.cast());
		
		System.out.println(ep.value().swigValue());
		
		
		// TODO Auto-generated method stub
		System.out.println(API.tobiigaze_get_version());
	}

}
