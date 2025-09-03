package co.com.pragma.consumer;

import co.com.pragma.consumer.api.model.request.UserExistsRequest;
import co.com.pragma.consumer.api.model.response.UserExistsResponse;
import co.com.pragma.consumer.constants.ExternalApiConstants;
import co.com.pragma.model.user.gateways.UserGateway;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RestConsumer implements UserGateway {
   
    private final WebClient client;
    
    @Override
    @CircuitBreaker(name = "checkUserExists")
    public Mono<Boolean> userExists(String email, String identificationNumber) {
        return client.post()
            .uri(
                uriBuilder -> uriBuilder
                    .path(ExternalApiConstants.USER_EXISTS)
                    .build()
            )
            .bodyValue(
               new UserExistsRequest(email, identificationNumber)
            )
            .retrieve()
            .bodyToMono(UserExistsResponse.class)
            .map(UserExistsResponse::exists);
    }
}
