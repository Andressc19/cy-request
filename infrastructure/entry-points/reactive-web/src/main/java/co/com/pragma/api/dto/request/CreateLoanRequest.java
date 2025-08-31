package co.com.pragma.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

@Schema(description = "Request para crear solicitud")
public record CreateLoanRequest(
	
	@Schema(description = "Cantidad a solicitar", example = "2500000")
	BigDecimal amount,
	
	@Schema(description = "Numero de identificacion", example = "1234567890")
	String identificationNumber,
	
	@NotBlank(message = "Debe ingresar un correo electrónico")
	@Email(message = "Debe ingresar un correo electrónico válido")
	@Schema(description = "Correo electrónico", example = "johndoe@mail.com")
	String email,
	
	@Schema(description = "Debe ingresar un periodo en meses", example ="12")
	Short periodMonths,
	
	@Schema(description = "Estado de la solicitud", example = "1")
	Short status,
	
	@Schema(description = "Tipo de prestamo", example = "1")
	Short loanType
) {}
