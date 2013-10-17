package tobii.util;

import java.util.Arrays;
import java.util.List;

/**
 * An n dimensional vector.
 * 
 * @author Ralf Biedert <rb@xr.io>
 */
public class Vn {

	/** The actual values */
	private final double[] values;

	public Vn(double... values) {
		this.values = values == null ? new double[0] : values;
	}

	public int dim() {
		return this.values.length;
	}

	public double get(int i) {
		if (i >= dim())
			return Double.NaN;
		return this.values[i];
	}

	public Vn mul(double v) {
		double x[] = new double[dim()];

		for (int i = 0; i < x.length; i++) {
			x[i] = get(i) * v;
		}

		return new Vn(x);
	}

	public Vn add(double v) {
		double x[] = new double[dim()];

		for (int i = 0; i < x.length; i++) {
			x[i] = get(i) + v;
		}

		return new Vn(x);
	}

	public Vn add(Vn v) {
		double x[] = new double[dim()];

		for (int i = 0; i < x.length; i++) {
			x[i] = get(i) + v.get(i);
		}

		return new Vn(x);
	}

	public V2 v2() {
		return new V2(get(0), get(1));
	}

	public V3 v3() {
		return new V3(get(0), get(1), get(2));
	}

	public static Vn random(int n) {
		double x[] = new double[n];
		for (int i = 0; i < x.length; i++) {
			x[i] = Math.random();
		}

		return new Vn(x);
	}
	
	public double sum() {
		double rval = 0;
		for (double d : this.values) {
			rval += d;
		}
		return rval;
	}
	
	
	public double median() {
		double[] ds = this.values.clone();		
		Arrays.sort(ds);
		return ds[ds.length / 2];
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < values.length; i++) {
			sb.append(values[i]);
			sb.append(", ");
		}
		sb.replace(sb.length() - 2, sb.length(), ")");
		return sb.toString();
	}
	
	
	public double length() {
		double rval = 0;
		
		for (int i = 0; i < this.dim(); i++) {
			rval += get(i) * get(i);
		}
		
		return Math.sqrt(rval);
	}

	
	public static Vn row(List<Vn> past, int i) {
		final double vals[] = new double[past.size()];
		
		int ptr = 0;
		
		for (Vn v : past) {
			vals[ptr++] = v.get(i);
		}
		
		return new Vn(vals); 
	}	
}
