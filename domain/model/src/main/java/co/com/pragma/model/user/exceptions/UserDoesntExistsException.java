package co.com.pragma.model.user.exceptions;

import co.com.pragma.model.exceptions.DomainException;

public class UserDoesntExistsException extends DomainException {
	public UserDoesntExistsException( String email, String identificationNumber) {
		super("User with email " + email + " and identification "+ identificationNumber+ " does not exist");
	}
}
