package co.com.pragma.model.loanstatus.exceptions;

import co.com.pragma.model.exceptions.DomainException;

public class LoanStatusNotExists extends DomainException {
	public LoanStatusNotExists(String name) {
		super("Status with id " + name + " does not exist");
	}
}
