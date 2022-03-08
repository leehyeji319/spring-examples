package recorder.backend.exception;

public class IllegalUserException extends RuntimeException {

	public IllegalUserException() {
		super();
	}

	public IllegalUserException(String message) {
		super(message);
	}

	public IllegalUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalUserException(Throwable cause) {
		super(cause);
	}
}
