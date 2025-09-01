package co.com.pragma.api.dto.response;

import java.math.BigDecimal;

public record CreateLoanResponse(
	BigDecimal amount,
	String identificationNumber,
	String email,
	Short periodMonths,
	Short loanStatusId,
	Short loanTypeId
){}
