package tobii;

import java.awt.GraphicsDevice;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import tobii.util.V2;
import tobii.util.Vn;

public class MouseTracker extends AbstractTracker {

	private Timer timer;

	@Override
	public Tracker connect() throws APIException {
		return this;
	}

	@Override
	public Tracker disconnect() throws APIException {
		stop();
		return this;
	}

	@Override
	public Tracker start() throws APIException {
		if (this.timer != null)
			return this;

		this.timer = new Timer();
		this.timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				final Point point = MouseInfo.getPointerInfo().getLocation();
				final GraphicsDevice device = MouseInfo.getPointerInfo().getDevice();
				
				double rx = point.getX() / device.getDisplayMode().getWidth();
				double ry = point.getY() / device.getDisplayMode().getHeight();
				
				V2 pos = new V2(rx, ry);
				
				V2 left = pos.add(Vn.random(2).add(-0.5).mul(0.01)).v2();
				V2 right = pos.add(Vn.random(2).add(-0.5).mul(0.01)).v2();
				
								
				final GazeEvent e = new GazeEvent(
						System.nanoTime(), 
						System.currentTimeMillis(),
						1,
						GazeEventEyeInfo.fake(left),
						GazeEventEyeInfo.fake(right));
				
				dispatchGazeEvent(e);
			}
		}, 0, 33);

		return this;
	}

	@Override
	public Tracker stop() throws APIException {
		if (this.timer == null)
			return this;
		
		this.timer.cancel();
		this.timer = null;
		return this;
	}

	@Override
	public String url() {
		return "tet-mouse://default";
	}
}
