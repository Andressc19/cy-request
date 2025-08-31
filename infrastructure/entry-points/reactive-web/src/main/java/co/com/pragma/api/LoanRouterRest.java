package co.com.pragma.api;

import co.com.pragma.api.dto.request.CreateLoanRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class LoanRouterRest {
    
    private static final String API_PATH = "/api/v1";
    
    @Bean
    @RouterOperations({
        @RouterOperation(
            path = API_PATH + "/solicitud",
            beanClass = LoanHandler.class,
            beanMethod = "listenGETCreateLoan",
            method = RequestMethod.POST,
            operation = @Operation(
                operationId = "createLoan",
                summary = "Agrega una solicitud de prestamo",
                requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                        schema = @Schema(implementation = CreateLoanRequest.class)
                    )
                ),
                responses = {
                    @ApiResponse(
                        responseCode = "201",
                        description = "Solicitud de prestamo creada",
                        content = @Content(schema = @Schema(implementation = CreateLoanRequest.class))
                    )
                }
            )
        )
    })
    public RouterFunction<ServerResponse> routerFunction(LoanHandler handler) {
        return route(POST(API_PATH + "/solicitud"), handler::listenGETCreateLoan);
    }
}
