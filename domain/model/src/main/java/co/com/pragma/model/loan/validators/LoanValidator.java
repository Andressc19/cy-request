package co.com.pragma.model.loan.validators;

import co.com.pragma.model.loan.Loan;
import co.com.pragma.model.loan.exceptions.LoanAmountOutRangeException;
import co.com.pragma.model.loantype.LoanType;

import java.math.BigDecimal;


public class LoanValidator {
	
	public static void validate(Loan loan, LoanType loanType){
		validateInRange(loan, loanType);
	}
	
	public static void validateInRange(Loan loan, LoanType loanType){
		
		BigDecimal loanAmount = loan.getAmount();
		BigDecimal loanMinAmount =  loanType.getMinAmount();
		BigDecimal loanMaxAmount = loanType.getMaxAmount();
		
		if (loanAmount.compareTo(loanMinAmount) < 0 || loanAmount.compareTo(loanMaxAmount) > 0) {
			throw new LoanAmountOutRangeException(loanAmount, loanMinAmount, loanMaxAmount);
		}
	}
	
}
