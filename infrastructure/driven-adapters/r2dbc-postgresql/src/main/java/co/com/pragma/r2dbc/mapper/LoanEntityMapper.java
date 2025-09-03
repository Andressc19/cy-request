package co.com.pragma.r2dbc.mapper;

import co.com.pragma.model.loan.Loan;
import co.com.pragma.model.loanstatus.LoanStatus;
import co.com.pragma.model.loantype.LoanType;
import co.com.pragma.r2dbc.entity.LoanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanEntityMapper {
    
    @Mapping(source = "statusId", target = "status")
    @Mapping(source = "typeId", target = "type")
    Loan toDomain(LoanEntity entity);
    
    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "type.id", target = "typeId")
    LoanEntity toEntity(Loan loan);
    
    default LoanStatus map(Short id) {
        if (id == null) return null;
        return LoanStatus.builder().id(id).build();
    }
    
    default LoanType mapLoanType(Short id) {
        if (id == null) return null;
        return LoanType.builder().id(id).build();
    }
    
}