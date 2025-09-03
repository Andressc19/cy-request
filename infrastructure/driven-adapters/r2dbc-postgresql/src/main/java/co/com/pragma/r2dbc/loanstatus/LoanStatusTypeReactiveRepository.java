package co.com.pragma.r2dbc.loanstatus;

import co.com.pragma.r2dbc.entity.LoanStatusEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface LoanStatusTypeReactiveRepository extends ReactiveCrudRepository <LoanStatusEntity, Short>, ReactiveQueryByExampleExecutor<LoanStatusEntity> {
	Mono<LoanStatusEntity> findByName(String name);
}
