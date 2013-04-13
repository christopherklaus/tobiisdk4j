package tobii.util;

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
}
