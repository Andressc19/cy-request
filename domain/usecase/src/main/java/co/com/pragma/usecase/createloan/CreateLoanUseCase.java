package co.com.pragma.usecase.createloan;

import co.com.pragma.model.loan.Loan;
import co.com.pragma.model.loan.gateways.LoanRepository;
import co.com.pragma.model.loanstatus.gateways.LoanStatusRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateLoanUseCase implements ICreateLoanUseCase {

    private final LoanRepository loanRepository;
    private final LoanStatusRepository loanStatusRepository;

    @Override
    public Mono<Loan> execute(Loan loan) {
//        return loanStatusRepository.existById(loan.getStatus().getId())
//            // TODO validar mediante el restconsumer si existe el usuario
//            .filter( )
        return Mono.empty();
        
    }
}
