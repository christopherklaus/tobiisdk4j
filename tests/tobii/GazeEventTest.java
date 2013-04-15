package tobii;

import static org.junit.Assert.*;

import org.junit.Test;

import tobii.util.V2;
import tobii.util.V3;

public class GazeEventTest {
	
	@Test
	public void testCenter() throws APIException {
		V3 lpm = new V3(100, -100, 100);
		V3 lpn = new V3(1, -1, 1);
		V3 lgm = new V3(100, -100, 100);
		V2 lgn = new V2(1, 1);

		V3 rpm = new V3(-100, 100, -100);
		V3 rpn = new V3(-1, 1, -1);
		V3 rgm = new V3(-100, 100, -100);
		V2 rgn = new V2(-1, 2);
		
		GazeEventEyeInfo left = new GazeEventEyeInfo(lpm, lpn, lgm, lgn);
		GazeEventEyeInfo right = new GazeEventEyeInfo(rpm, rpn, rgm, rgn);
		
		GazeEvent event = new GazeEvent(100, 10, 1, left, right);
		
		GazeEventEyeInfo center = event.center();
		
		
		assertEquals(center.eyePosFromTrackerMM.sum(), 0, 0.001);
		assertEquals(center.eyePosInBoxNorm.sum(), 0, 0.001);
		assertEquals(center.gazeFromTrackerMM.sum(), 0, 0.001);
		assertEquals(center.gazeOnDisplayNorm.sum(), 1.5, 0.001);		
	}	
}
