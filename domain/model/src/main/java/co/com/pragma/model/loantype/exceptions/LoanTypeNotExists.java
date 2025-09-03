package co.com.pragma.model.loantype.exceptions;

import co.com.pragma.model.exceptions.DomainException;

public class LoanTypeNotExists extends DomainException {
	public LoanTypeNotExists(Short loanTypeId) {
		super("Type with id " + loanTypeId + " does not exist");
	}
}
