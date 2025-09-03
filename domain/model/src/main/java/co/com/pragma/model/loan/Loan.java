package co.com.pragma.model.loan;
import co.com.pragma.model.loanstatus.LoanStatus;
import co.com.pragma.model.loantype.LoanType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Loan {
	
	private Long id;
	private BigDecimal amount;
	private String identificationNumber;
	private String email;
	private Short periodMonths;
	private LoanStatus status;
	private LoanType type;
	private LocalDateTime approvedAt;
}