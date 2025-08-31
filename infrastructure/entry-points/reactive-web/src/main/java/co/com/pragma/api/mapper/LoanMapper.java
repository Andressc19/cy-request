package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.request.CreateLoanRequest;
import co.com.pragma.api.dto.response.CreateLoanResponse;
import co.com.pragma.model.loan.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    @Mapping(source="", target = "")
    Loan toDomain(CreateLoanRequest request);
    CreateLoanResponse toDto(Loan loan);
}

