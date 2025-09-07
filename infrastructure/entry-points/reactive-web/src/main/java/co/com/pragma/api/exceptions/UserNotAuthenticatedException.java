package co.com.pragma.api.exceptions;

public class UserNotAuthenticatedException extends RuntimeException {
	public UserNotAuthenticatedException(String email) {
		super("User with" + email + " is not authenticated");
	}
}
