package co.com.pragma.r2dbc.loan;

import co.com.pragma.model.loan.Loan;
import co.com.pragma.model.loan.gateways.LoanRepository;
import co.com.pragma.r2dbc.entity.LoanEntity;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import co.com.pragma.r2dbc.mapper.LoanEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@Repository
@Slf4j
public class LoanReactiveRepositoryAdapter extends ReactiveAdapterOperations
    <Loan, LoanEntity, Long, LoanReactiveRepository> implements LoanRepository {

    private final TransactionalOperator transactionalOperator;
    private final LoanEntityMapper loanEntityMapper;

    public LoanReactiveRepositoryAdapter(
          LoanReactiveRepository repository,
          ObjectMapper mapper,
          TransactionalOperator transactionalOperator, LoanEntityMapper loanEntityMapper
    ) {
        super(repository, mapper, loanEntityMapper::toDomain);
        this.transactionalOperator = transactionalOperator;
        this.loanEntityMapper = loanEntityMapper;
    }

    @Override
    public Mono<Loan> saveLoan(Loan loan) {
        return save(loan)
              .as(transactionalOperator::transactional)
              .doOnSuccess(saved -> log.info("Loan saved {}", saved))
              .doOnError(e -> log.error("Error saving loan request", e));
    }

    @Override
    protected LoanEntity toData(Loan user) {
        return loanEntityMapper.toEntity(user);
    }
}
