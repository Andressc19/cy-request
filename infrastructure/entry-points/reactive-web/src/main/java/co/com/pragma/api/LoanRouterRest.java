package co.com.pragma.api;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class LoanRouterRest {
    
    private static final String API_PATH = "/api/v1";
    
    @Bean
    @RouterOperations({
        @RouterOperation(
            path = API_PATH + "/solicitud"
        )
    })
    public RouterFunction<ServerResponse> routerFunction(LoanHandler handler) {
        return route(GET(API_PATH + "/solicitud"), handler::listenGETUseCase);
    }
}
