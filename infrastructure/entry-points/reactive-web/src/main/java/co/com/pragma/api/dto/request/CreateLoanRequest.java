package co.com.pragma.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Request para crear solicitud")
public record CreateLoanRequest(
	
	@NotNull(message = "Debe ingresar un monto")
	@Schema(description = "Cantidad a solicitar", example = "2500000")
	BigDecimal amount,
	
	@NotBlank(message = "Debe ingresar un número de identificación")
	@Schema(description = "Número de identificación", example = "1234567890")
	String identificationNumber,
	
	@NotBlank(message = "Debe ingresar un correo electrónico")
	@Email(message = "Debe ingresar un correo electrónico válido")
	@Schema(description = "Correo electrónico", example = "johndoe@mail.com")
	String email,
	
	@NotNull(message = "Debe ingresar un periodo en meses")
	@Schema(description = "Debe ingresar un periodo en meses", example ="12")
	Short periodMonths,
	
	@NotNull(message = "Debe seleccionar un tipo de préstamo")
	@Schema(description = "Tipo de préstamo", example = "1")
	Short typeId

) {}