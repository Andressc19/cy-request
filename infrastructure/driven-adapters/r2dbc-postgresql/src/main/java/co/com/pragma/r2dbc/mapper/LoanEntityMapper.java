package co.com.pragma.r2dbc.mapper;

import co.com.pragma.model.loan.Loan;
import co.com.pragma.r2dbc.entity.LoanEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanEntityMapper {
    Loan toDomain(LoanEntity loanEntity);
    LoanEntity toEntity(Loan loan);
}
