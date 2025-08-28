package co.com.pragma.r2dbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("estado_prestamo")
public class LoanStatusEntity {
	
	@Id
	@Column("id_estado")
	private Long id;
	
	@Column("nombre")
	private String name;
	
	@Column("descripcion")
	private String description;
}
