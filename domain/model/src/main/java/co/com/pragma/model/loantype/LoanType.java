package co.com.pragma.model.loantype;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LoanType {
	private Short id;
	private String name;
	private BigDecimal minAmount;
	private BigDecimal maxAmount;
	private BigDecimal interestRate;
	private boolean autoValidation;
}