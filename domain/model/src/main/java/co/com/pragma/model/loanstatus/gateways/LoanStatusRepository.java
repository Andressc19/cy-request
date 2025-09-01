package co.com.pragma.model.loanstatus.gateways;

import reactor.core.publisher.Mono;

public interface LoanStatusRepository {
	Mono<Boolean> existById(Short id);
}
