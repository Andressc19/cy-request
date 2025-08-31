package co.com.pragma.r2dbc.entity;

import jakarta.persistence.PrePersist;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
	
	@Column("id_estado")
	private Long idStatus;
	
	@Column("id_tipo_prestamo")
	private Long idLoanType;

	@Column("fecha_aprobacion")
	private LocalDateTime approvedAt;

	@Column("fecha_creacion")
	@PrePersist
	private LocalDateTime createdAt;
}
