package exceptions;

public class BadLengthTelephoneNumberException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadLengthTelephoneNumberException() {
		super(new Throwable("Bad length of telephone number"));
	}
}
