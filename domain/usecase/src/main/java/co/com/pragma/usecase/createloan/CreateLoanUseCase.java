package co.com.pragma.usecase.createloan;

import co.com.pragma.model.loan.Loan;
import co.com.pragma.model.loan.gateways.LoanRepository;
import co.com.pragma.model.loan.validators.LoanValidator;
import co.com.pragma.model.loanstatus.LoanStatus;
import co.com.pragma.model.loanstatus.constants.LoanStatusConstants;
import co.com.pragma.model.loanstatus.exceptions.LoanStatusNotExists;
import co.com.pragma.model.loanstatus.gateways.LoanStatusRepository;
import co.com.pragma.model.loantype.LoanType;
import co.com.pragma.model.loantype.exceptions.LoanTypeNotExists;
import co.com.pragma.model.loantype.gateways.LoanTypeRepository;
import co.com.pragma.model.user.exceptions.UserDoesntExistsException;
import co.com.pragma.model.user.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateLoanUseCase implements ICreateLoanUseCase {

    private final LoanRepository loanRepository;
    private final LoanStatusRepository loanStatusRepository;
    private final LoanTypeRepository loanTypeRepository;
    private final UserGateway userGateway;
    
    @Override
    public Mono<Loan> execute(Loan loan, String token) {
        
        ///  Validate if exists loan type
        Mono<LoanType> loanTypeExists = loanTypeRepository.findById(loan.getType().getId())
            .switchIfEmpty(Mono.error(new LoanTypeNotExists(loan.getType().getId())));
        
        /// Validate if exists loan status
        Mono<LoanStatus> pendingStatus = loanStatusRepository.findByName(LoanStatusConstants.PENDING)
            .switchIfEmpty(Mono.error(new LoanStatusNotExists(LoanStatusConstants.PENDING)));
        
        ///  validate if user exists
        Mono<Boolean> userExists = userGateway.userExists(token)
            .filter(exist -> exist)
            .switchIfEmpty(Mono.error(new UserDoesntExistsException(loan.getEmail(), loan.getIdentificationNumber())));
        
        return Mono.zip(loanTypeExists, pendingStatus, userExists)
            .flatMap(tuple -> {
                LoanType type = tuple.getT1();
                LoanStatus status = tuple.getT2();
                loan.setStatus(status);
                
                LoanValidator.validate(loan, type);
                
                return loanRepository.saveLoan(loan);
            });
    }
}
