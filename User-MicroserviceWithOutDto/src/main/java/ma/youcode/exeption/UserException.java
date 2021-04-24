package ma.youcode.exeption;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 6935467431721129576L;

	public UserException(String message) {
		super(message);
	}
}
