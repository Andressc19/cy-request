package co.com.pragma.model.loan;
import co.com.pragma.model.loanstatus.LoanStatus;
import co.com.pragma.model.loantype.LoanType;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Loan{
	private Long id;
	private BigDecimal amount;
	private String email;
	private int periodMonths;
	private LoanStatus status;
	private LoanType loanType;
}
