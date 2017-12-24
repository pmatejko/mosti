package exceptions;

public class UnsupportedDataProviderException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsupportedDataProviderException() {
        super();
    }

    public UnsupportedDataProviderException(String message) {
        super(message);
    }
}
