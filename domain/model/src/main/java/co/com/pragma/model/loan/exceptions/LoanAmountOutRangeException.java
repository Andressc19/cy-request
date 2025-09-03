package co.com.pragma.model.loan.exceptions;

import co.com.pragma.model.exceptions.DomainException;

import java.math.BigDecimal;

public class LoanAmountOutRangeException extends DomainException {
	public LoanAmountOutRangeException(BigDecimal amount, BigDecimal min, BigDecimal max) {
		super("The amount " + amount + " is out of range " + min + " and " + max);
	}
}
