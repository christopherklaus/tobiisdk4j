package tobii.filter;

import java.util.LinkedList;
import java.util.List;

import tobii.util.V2;
import tobii.util.Vn;

public abstract class Filter {
	protected Filter() {}
	
	public static Filter NONE = new Filter() {
		@Override
		public Vn filter(Vn in) {
			return in;
		}		
	};
	
	public static Filter MEDIAN = MEDIAN(3);
	
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
				
				return new Vn(x, y);
			}
		};		
	}	
	
	
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
