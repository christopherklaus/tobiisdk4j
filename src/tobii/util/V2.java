package tobii.util;

import java.awt.Point;

public class V2 extends Vn {
	public V2(double x, double y) {
		super(x, y);
	}
	
	public V2(Point p) {
		this(p.x, p.y);
	}	
	
	public double x() {
		return get(0);
	}
	
	public double y() {
		return get(1);
	}
	
	public Point point() {
		return new Point((int) x(), (int) y());
	}

	public double atan2() {
		return Math.atan2(y(), x());
	}
	
	public double dist(V2 other) {
		double dx = x() - other.x();
		double dy = y() - other.y();		
		return Math.sqrt(dx*dx + dy*dy);
	}
}
