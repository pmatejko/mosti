package notifier.exceptions;

public class BadLengthTelephoneNumberException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadLengthTelephoneNumberException() {
		super("Telephone number too short or too long!");

	}
}
