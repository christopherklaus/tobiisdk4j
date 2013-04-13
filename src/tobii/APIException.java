package tobii;

/**
 * An eye tracking exception from the low level API.
 * 
 * @author Ralf Biedert <rb@xr.io>
 */
public class APIException extends Exception {
	/** */
	private static final long serialVersionUID = 1308038100484812604L;
	
	private final int value;
	
	private final String message;

	
	public APIException(int value, String message) {
		this.value = value;
		this.message = message;
	}

	/**
	 * Returns the value of the exception.
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}	
}
