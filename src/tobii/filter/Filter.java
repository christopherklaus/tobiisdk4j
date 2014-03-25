package tobii.filter;

import java.util.LinkedList;
import java.util.List;

import tobii.util.Vn;

/**
 * Base class for all filters
 * 
 * @author Ralf Biedert <rb@xr.io>
 *
 */
public abstract class Filter {
	protected Filter() {}

	/** No filter */
	public static Filter NONE = new Filter() {
		@Override
		public Vn filter(Vn in) {
			return in;
		}		
	};
	
	/**
	 * Chains filter. Filters passes first will be called first, i.e., 
	 * <code>CHAIN(MEDIAN(3), AVERAGE(3))</code> will first perform a median filter,
	 * then an average filter.
	 *   
	 * @param filters
	 * @return
	 */
	public static Filter CHAIN(final Filter ... filters) {
		return new Filter() {		
			
			@Override
			public Vn filter(Vn in) {				
				for (Filter filter : filters) {
					in = filter.filter(in);
				}				
				return in;
			}
		};		
	}
	

	
	/**
	 * Constructs a median filter of length N
	 * 
	 * @param N
	 * @return
	 */
	public static Filter MEDIAN(final int N) {
		return new Filter() {		
			private List<Vn> past = new LinkedList<Vn>();
			
			@Override
			public Vn filter(Vn in) {
				past.add(in);
				
				if(past.size() > N) 
					past.remove(0);
				
				if(past.size() < N) 
					return in;
	
				// FIXME: Not considering all dimensions
				double x = Vn.row(past, 0).median();
				double y = Vn.row(past, 1).median();
				
				if (in.dim() == 3) {
					double z = Vn.row(past, 2).median();
					return new Vn(x, y, z);
				}
				
				return new Vn(x, y);				
			}
		};		
	}	
	
	
	/**
	 * Constructs an average filter of length N
	 * 
	 * @param N
	 * @return
	 */
	public static Filter AVERAGE(final int N) {
		return new Filter() {		
			private List<Vn> past = new LinkedList<Vn>();
			
			@Override
			public Vn filter(Vn in) {
				past.add(in);
				
				if(past.size() > N) 
					past.remove(0);
				
				if(past.size() < N) 
					return in;
				
				Vn rval = this.past.get(0);
				for (int i = 1; i < this.past.size(); i++) {
					rval = rval.add(this.past.get(i));					
				}
				
				return rval.mul(1.0 / this.past.size());
			}
		};		
	}	
	

	/** 
	 * Called to filter some vector.
	 * 
	 * @param in The vector to filter.
	 * @return A filtered vector.
	 */
	public abstract Vn filter(Vn in);
}
