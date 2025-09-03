package co.com.pragma.usecase.createloan;

import co.com.pragma.model.loan.Loan;
import co.com.pragma.model.loan.gateways.LoanRepository;
import co.com.pragma.model.loanstatus.LoanStatus;
import co.com.pragma.model.loanstatus.constants.LoanStatusConstants;
import co.com.pragma.model.loanstatus.exceptions.LoanStatusNotExists;
import co.com.pragma.model.loanstatus.gateways.LoanStatusRepository;
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
    public Mono<Loan> execute(Loan loan) {
        
        ///  Validate if exists loan type
        Mono<Boolean> loanTypeExists = loanTypeRepository.existsById(loan.getType().getId())
            .filter(exist-> exist)
            .switchIfEmpty(Mono.error(new LoanTypeNotExists(loan.getType().getId())));
        
        /// Validate if exists loan status
        Mono<LoanStatus> pendingStatus = loanStatusRepository.findByName(LoanStatusConstants.PENDING)
            .switchIfEmpty(Mono.error(new LoanStatusNotExists(LoanStatusConstants.PENDING)));
        
        ///  validate if user exists
        Mono<Boolean> userExists = userGateway.userExists(loan.getEmail(), loan.getIdentificationNumber())
            .filter(exist -> exist)
            .switchIfEmpty(Mono.error(new UserDoesntExistsException(loan.getEmail(), loan.getIdentificationNumber())));
        
        return Mono.zip(loanTypeExists, pendingStatus, userExists)
            .flatMap(tuple -> {
                LoanStatus status = tuple.getT2();
                loan.setStatus(status);
                return loanRepository.saveLoan(loan);
            });
    }
}
