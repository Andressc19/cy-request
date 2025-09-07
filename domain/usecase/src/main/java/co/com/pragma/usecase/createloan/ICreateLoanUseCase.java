package co.com.pragma.usecase.createloan;

import co.com.pragma.model.loan.Loan;
import reactor.core.publisher.Mono;

public interface ICreateLoanUseCase {
    Mono<Loan> execute(Loan loan, String token);
}
