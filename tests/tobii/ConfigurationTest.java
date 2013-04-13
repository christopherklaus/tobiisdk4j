package tobii;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ConfigurationTest {
	
	@Test
	public void testDeviceAddress() throws APIException {
		assertNotNull(Configuration.exceptionMessage(0));
		//System.out.println(Configuration.exceptionMessage(1));
	}
	

}
