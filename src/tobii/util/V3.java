package tobii.util;

public class V3 extends Vn {
	public V3(double x, double y, double z) {
		super(x, y, z);
	}
	
	public double x() {
		return get(0);
	}
	
	public double y() {
		return get(1);
	}
	
	public double z() {
		return get(2);
	}

}
