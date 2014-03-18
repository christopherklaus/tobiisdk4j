package tobii;

import static org.junit.Assert.*;

import org.junit.Test;

import tobii.filter.Filter;
import tobii.util.Vn;

public class FilterTest {
	
	@Test
	public void testMedian() {
						
		Filter m = Filter.MEDIAN(3);
		m.filter(new Vn(1, 4));
		m.filter(new Vn(3, 6));
		Vn res = m.filter(new Vn(2, 0));
		
		System.out.println(res);
		assertEquals(2, res.get(0), 0.001);
		assertEquals(4, res.get(1), 0.001);

	}
	
	@Test
	public void testAverage() {
						
		Filter m = Filter.AVERAGE(3);
		m.filter(new Vn(1, 4));
		m.filter(new Vn(3, 6));

		Vn res = m.filter(new Vn(11, 5));
		
		System.out.println(res);
		assertEquals(5, res.get(0), 0.001);
		assertEquals(5, res.get(1), 0.001);
	}	
	
}
