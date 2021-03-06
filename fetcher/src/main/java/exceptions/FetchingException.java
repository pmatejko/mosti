package exceptions;

public class FetchingException extends RuntimeException {

    public FetchingException(Throwable cause) {
        super(cause);
    }

    public FetchingException(String message) {
        super(message);
    }

    public FetchingException(String message, Throwable cause) {
        super(message, cause);
    }
}
