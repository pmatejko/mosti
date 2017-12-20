package exceptions;

public class FetchingException extends Exception {

    public FetchingException(Throwable cause) {
        super(cause);
    }

    public FetchingException(String message, Throwable cause) {
        super(message, cause);
    }
}
