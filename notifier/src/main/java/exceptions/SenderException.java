package exceptions;

public class SenderException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Throwable cause;
	
	public SenderException(Throwable cause) {
		super("sender exception: \n", cause);
		this.cause = cause;
	}
	
	public Throwable getCause(){
		return this.cause;
	}
}
