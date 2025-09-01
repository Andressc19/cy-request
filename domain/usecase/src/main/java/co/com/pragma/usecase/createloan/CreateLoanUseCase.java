package co.com.pragma.usecase.createloan;

import co.com.pragma.model.loan.Loan;
import co.com.pragma.model.loan.gateways.LoanRepository;
import co.com.pragma.model.loanstatus.LoanStatus;
import co.com.pragma.model.loanstatus.constants.LoanStatusConstants;
import co.com.pragma.model.loanstatus.exceptions.LoanStatusNotExists;
import co.com.pragma.model.loanstatus.gateways.LoanStatusRepository;
import co.com.pragma.model.loantype.exceptions.LoanTypeNotExists;
import co.com.pragma.model.loantype.gateways.LoanTypeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateLoanUseCase implements ICreateLoanUseCase {

    private final LoanRepository loanRepository;
    private final LoanStatusRepository loanStatusRepository;
    private final LoanTypeRepository loanTypeRepository;
    
    @Override
    public Mono<Loan> execute(Loan loan) {
        
        Mono<Boolean> loanTypeExists = loanTypeRepository.existsById(loan.getType().getId())
            .filter(exist-> exist)
            .switchIfEmpty(Mono.error(new LoanTypeNotExists(loan.getType().getId())));
        
        Mono<LoanStatus> pendingStatus = loanStatusRepository.findByName(LoanStatusConstants.PENDING)
            .switchIfEmpty(Mono.error(new LoanStatusNotExists(LoanStatusConstants.PENDING)));
        
        return loanTypeExists
            .then(pendingStatus)
            .flatMap(status -> {
                loan.setStatus(status);
                return loanRepository.saveLoan(loan);
            });
    }
}
