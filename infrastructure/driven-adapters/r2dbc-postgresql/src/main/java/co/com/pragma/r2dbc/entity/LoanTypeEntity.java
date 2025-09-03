package co.com.pragma.r2dbc.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Table("tipo_prestamo")
public class LoanTypeEntity {
	
	@Id
	@Column("id_tipo_prestamo")
	private Long id;
	
	@Column("nombre")
	private String name;
	
	@Column("monto_minimo")
	private BigDecimal minAmount;
	
	@Column("monto_maximo")
	private BigDecimal maxAmount;
	
	@Column("tasa_interes")
	private BigDecimal interestRate;
	
	@Column("validacion_automatica")
	private boolean amount;
}
