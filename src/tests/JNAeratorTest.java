package tests;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.Pointer.StringType;

import tobii.lowlevel.config.TobiiSDKConfigLibrary;
import tobii.lowlevel.sdk.TobiiSDKLibrary;
import tobii.lowlevel.sdk.tobiigaze_gaze_data;
import tobii.lowlevel.sdk.TobiiSDKLibrary.tobiigaze_eye_tracker;
import tobii.lowlevel.sdk.TobiiSDKLibrary.tobiigaze_gaze_listener;

public class JNAeratorTest {

	public static class CB extends tobiigaze_gaze_listener {
		@Override
		public void apply(Pointer<tobiigaze_gaze_data> gaze_data,
				Pointer<?> user_data) {
			tobiigaze_gaze_data data = gaze_data.apply(0);
			System.out.println(data.timestamp());
			System.out.println(data.tracking_status().value());
			//data.left().
		}
	}	
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		
		CB xx = new CB();
		
		//TobiiSDKLibrary.tobiigaze_start_tracking(tracker, (Pointer<TobiiSDKLibrary.tobiigaze_gaze_listener>) xx.toPointer(), error, null);

		Thread.sleep(100000000);
	}

}
