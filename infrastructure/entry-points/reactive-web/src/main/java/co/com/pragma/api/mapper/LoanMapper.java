package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.request.CreateLoanRequest;
import co.com.pragma.api.dto.response.CreateLoanResponse;
import co.com.pragma.model.loan.Loan;
import co.com.pragma.model.loanstatus.LoanStatus;
import co.com.pragma.model.loantype.LoanType;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    @Mapping(source="loanStatusId", target = "status", qualifiedByName = "mapStatus")
    @Mapping(source="loanTypeId", target = "loanType", qualifiedByName = "mapLoanType")
    Loan toDomain(CreateLoanRequest request);
    
    @Mapping(source="loanType.id", target = "loanTypeId")
    @Mapping(source="status.id", target = "loanStatusId" )
    CreateLoanResponse toDto(Loan loan);
    
    
    @Named("mapLoanType")
    default LoanType mapLoanType(Short loanId) {
        if (loanId == null) return null;
        return LoanType.builder()
            .id(loanId)
            .build();
    }
    
    @Named("mapStatus")
    default LoanStatus mapStatus(Short statusId) {
        if (statusId == null) return null;
        return LoanStatus.builder()
            .id(statusId)
            .build();
    }
}

