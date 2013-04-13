package tobii;

import java.util.LinkedList;
import java.util.List;

abstract class AbstractTracker implements Tracker {
	protected final List<GazeListener> listener;

	public AbstractTracker() {
		this.listener = new LinkedList<GazeListener>();
	}
	
	@Override
	public Tracker register(GazeListener listener) {
		synchronized (this.listener) {
			this.listener.add(listener);
		}
		return this;
	}
	
	@Override
	public Tracker deregister(GazeListener listener) {
		synchronized (this.listener) {
			this.listener.remove(listener);
		}			
		return this;
	}
	
	
	protected void dispatchGazeEvent(GazeEvent e) {
		synchronized (listener) {
			for (GazeListener l : listener) {
				l.gazeEvent(e);
			}
		}			
	}
}
