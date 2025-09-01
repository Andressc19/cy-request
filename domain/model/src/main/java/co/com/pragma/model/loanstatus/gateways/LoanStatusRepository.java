package co.com.pragma.model.loanstatus.gateways;

import co.com.pragma.model.loanstatus.LoanStatus;
import reactor.core.publisher.Mono;

public interface LoanStatusRepository {
	Mono<LoanStatus> findByName(String name);
}
