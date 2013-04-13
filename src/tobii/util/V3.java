package tobii.util;

public class V3 extends Vn {
	public V3(double x, double y, double z) {
		super(x, y, z);
	}
	
	public double X() {
		return get(0);
	}
	
	public double Y() {
		return get(1);
	}
	
	public double Z() {
		return get(2);
	}
}
