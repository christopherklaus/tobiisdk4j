package tests;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.Pointer.StringType;

import tobiisdk.TobiiSDKLibrary;
import tobiisdk.tobiigaze_gaze_data;
import tobiisdk.TobiiSDKLibrary.tobiigaze_eye_tracker;
import tobiisdk.TobiiSDKLibrary.tobiigaze_gaze_listener;
import tobiisdkconfig.TobiiSDKConfigLibrary;

public class JNAeratorTest {

	public static class CB extends tobiigaze_gaze_listener {
		@Override
		public void apply(Pointer<tobiigaze_gaze_data> gaze_data,
				Pointer<?> user_data) {
			tobiigaze_gaze_data data = gaze_data.apply(0);
			System.out.println(data.timestamp());
			System.out.println(data.tracking_status().value());
			
		}
	}	
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println(TobiiSDKLibrary.tobiigaze_get_version().getCString());
				
		Pointer error = Pointer.allocateInt();
		
		TobiiSDKConfigLibrary.tobiigaze_config_init((Pointer<IntValuedEnum<TobiiSDKConfigLibrary.tobiigaze_error_code >>) error);
		System.out.println(error.getInt());

		Pointer url = Pointer.allocateChars(128);
		
		TobiiSDKConfigLibrary.tobiigaze_config_get_default_eye_tracker_url(url, 64, error);
		System.out.println(url.getString(StringType.C));		
		System.out.println(error.getInt());
		
		
		String deviceurl = url.getString(StringType.C);
		
		final Pointer<tobiigaze_eye_tracker> tracker = TobiiSDKLibrary.tobiigaze_create(url);
		
		//Pointer chars = Pointer.allocateChars(1000);
		//chars.setString("tet-tcp://XL060-001-83900001.local.", StringType.C);		
		//final Pointer<tobiigaze_eye_tracker> tracker = TobiiSDKLibrary.tobiigaze_create((Pointer<Byte>) chars);
		
		Thread t = new Thread(new Runnable() {			
			@Override
			public void run() {
				System.out.println(123);
				Pointer ee = Pointer.allocateInt();
				TobiiSDKLibrary.tobiigaze_run_event_loop(tracker, ee);
			}
		});
		t.setDaemon(true);
		t.start();
				
		
		
		TobiiSDKLibrary.tobiigaze_connect(tracker, error);
		System.out.println(error.getInt());
	
		CB xx = new CB();
		
		TobiiSDKLibrary.tobiigaze_start_tracking(tracker, (Pointer<TobiiSDKLibrary.tobiigaze_gaze_listener>) xx.toPointer(), error, null);

		Thread.sleep(100000000);
	}

}
