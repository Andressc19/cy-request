package co.com.pragma.r2dbc.loan;

import co.com.pragma.model.loan.Loan;
import co.com.pragma.model.loan.gateways.LoanRepository;
import co.com.pragma.r2dbc.entity.LoanEntity;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LoanReactiveRepositoryAdapter extends ReactiveAdapterOperations
    <Loan, LoanEntity, Long, LoanReactiveRepository> implements LoanRepository {
    
    public LoanReactiveRepositoryAdapter(LoanReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Loan.class));
    }
    
}
