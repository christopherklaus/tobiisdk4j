package tobii.util;

public class V2 extends Vn {
	public V2(double x, double y) {
		super(x, y);
	}
	
	public double X() {
		return get(0);
	}
	
	public double Y() {
		return get(1);
	}

}