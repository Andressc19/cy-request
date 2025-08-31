package co.com.pragma.api;

import co.com.pragma.api.dto.request.CreateLoanRequest;
import co.com.pragma.api.exceptions.RequestValidator;
import co.com.pragma.api.mapper.LoanMapper;
import co.com.pragma.usecase.createloan.CreateLoanUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
@Slf4j
@RequiredArgsConstructor
public class LoanHandler {

    private final RequestValidator jakartaValidator;
    private final CreateLoanUseCase createLoanUseCase;
    private final LoanMapper loanMapper;

    public Mono<ServerResponse> listenGETCreateLoan(ServerRequest request) {
        return request.bodyToMono(CreateLoanRequest.class)
              .flatMap(jakartaValidator::validate)
              .map(loanMapper::toDomain)
              .flatMap(createLoanUseCase::execute)
              .map(loanMapper::toDto)
              .doOnNext(user -> log.info("Created loan successfully: {}", user))
              .flatMap(dto -> ServerResponse.status(HttpStatus.CREATED).bodyValue(dto));

    }
}
