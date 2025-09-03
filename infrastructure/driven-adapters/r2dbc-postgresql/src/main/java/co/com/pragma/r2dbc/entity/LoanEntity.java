package co.com.pragma.r2dbc.entity;

import jakarta.persistence.PrePersist;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Table(name = "solicitud_prestamo")
public class LoanEntity {
	
	@Id
	@Column("id_solicitud")
	private Long id;
	
	@Column("monto")
	private BigDecimal amount;
	
	@Column("plazo")
	private Long periodMonths;
	
	@Column("email")
	private String email;
	
	@Column("numero_identificacion")
	private String identificationNumber;
	
	@Column("id_estado")
	private Long statusId;
	
	@Column("id_tipo_prestamo")
	private Long typeId;

	@Column("fecha_aprobacion")
	private LocalDateTime approvedAt;
}
