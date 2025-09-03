package co.com.pragma.r2dbc.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("estado_prestamo")
public class LoanStatusEntity {
	
	@Id
	@Column("id_estado")
	private Short id;
	
	@Column("nombre")
	private String name;
	
	@Column("descripcion")
	private String description;
}
