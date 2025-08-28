package co.com.pragma.r2dbc.loanstatus;

import co.com.pragma.model.loanstatus.LoanStatus;
import co.com.pragma.model.loantype.LoanType;
import co.com.pragma.model.loantype.gateways.LoanTypeRepository;
import co.com.pragma.r2dbc.entity.LoanStatusEntity;
import co.com.pragma.r2dbc.entity.LoanTypeEntity;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LoanTypeReactiveRepositoryAdapter extends ReactiveAdapterOperations
    <LoanStatus, LoanStatusEntity, Long, LoanStatusTypeReactiveRepository> implements LoanTypeRepository {
    
    public LoanTypeReactiveRepositoryAdapter(LoanStatusTypeReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, LoanStatus.class));
    }
    
}
