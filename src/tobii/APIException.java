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
	
	public APIException(int value) {
		this.value = value;
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
