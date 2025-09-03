package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.request.CreateLoanRequest;
import co.com.pragma.api.dto.response.CreateLoanResponse;
import co.com.pragma.model.loan.Loan;
import co.com.pragma.model.loantype.LoanType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    
    @Mapping(source="typeId", target = "type", qualifiedByName = "mapLoanType")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "approvedAt", ignore = true)
    Loan toDomain(CreateLoanRequest request);
    
    @Mapping(source="type.id", target = "typeId")
    @Mapping(source="status.id", target = "statusId" )
    CreateLoanResponse toDto(Loan loan);
    
    
    @Named("mapLoanType")
    default LoanType mapLoanType(Short loanId) {
        if (loanId == null) return null;
        return LoanType.builder()
            .id(loanId)
            .build();
    }
}

