package co.com.pragma.usecase.createloan;

import co.com.pragma.model.loan.Loan;
import co.com.pragma.model.loan.gateways.LoanRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateLoanUseCase implements ICreateLoanUseCase {

    private LoanRepository loanRepository;

    @Override
    public Mono<Loan> execute(Loan loan) {
        return loanRepository.saveLoan(loan);
    }
}
