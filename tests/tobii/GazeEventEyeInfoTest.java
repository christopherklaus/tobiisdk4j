package tobii;

import static org.junit.Assert.*;

import org.junit.Test;

import tobii.util.V2;

public class GazeEventEyeInfoTest {
	
	@Test
	public void testValidity() throws APIException {
		assertFalse(GazeEventEyeInfo.createInvalid().validByValues());
		assertTrue(GazeEventEyeInfo.fake(new V2(-1, -1)).validByValues());
		assertTrue(GazeEventEyeInfo.fake(new V2(0, 0)).validByValues());			
	}		
}
