package tobii;

public interface Tracker {
	/**
	 * Connects to the given device. 
	 * 
	 * @return
	 * 
	 * @throws APIException
	 */
	public Tracker connect() throws APIException;	

	public Tracker disconnect() throws APIException;	
	
	public Tracker start() throws APIException;
	
	public Tracker stop() throws APIException;

	public Tracker register(GazeListener listener);
	
	public Tracker deregister(GazeListener listener);	

}
