package exceptions;

public class UnsupportedDataProviderException extends RuntimeException {

    public UnsupportedDataProviderException() {
        super();
    }

    public UnsupportedDataProviderException(String message) {
        super(message);
    }
}
