package co.com.pragma.r2dbc.loanstatus;

import co.com.pragma.model.loanstatus.LoanStatus;
import co.com.pragma.model.loanstatus.gateways.LoanStatusRepository;
import co.com.pragma.r2dbc.entity.LoanStatusEntity;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import co.com.pragma.r2dbc.mapper.LoanStatusEntityMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class LoanStatusReactiveRepositoryAdapter extends ReactiveAdapterOperations
    <LoanStatus, LoanStatusEntity, Short, LoanStatusTypeReactiveRepository> implements LoanStatusRepository {
    
    private final LoanStatusEntityMapper loanStatusEntityMapper;
    
    public LoanStatusReactiveRepositoryAdapter(
        LoanStatusTypeReactiveRepository repository,
        ObjectMapper mapper,
        LoanStatusEntityMapper loanStatusEntityMapper
    ) {
        super(repository, mapper, d -> mapper.map(d, LoanStatus.class));
		this.loanStatusEntityMapper = loanStatusEntityMapper;
	}
    
    @Override
    public Mono<LoanStatus> findByName(String name) {
        return repository.findByName(name)
            .map(loanStatusEntityMapper::toDomain);
    }
}
