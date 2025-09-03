package co.com.pragma.r2dbc.mapper;

import co.com.pragma.model.loanstatus.LoanStatus;
import co.com.pragma.r2dbc.entity.LoanStatusEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanStatusEntityMapper {
	LoanStatusEntity toEntity(LoanStatus domain);
	LoanStatus toDomain(LoanStatusEntity entity);
}
