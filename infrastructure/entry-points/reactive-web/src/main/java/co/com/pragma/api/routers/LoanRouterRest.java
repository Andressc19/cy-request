package co.com.pragma.api.routers;

import co.com.pragma.api.constants.ApiConstants;
import co.com.pragma.api.dto.request.CreateLoanRequest;
import co.com.pragma.api.handlers.LoanHandler;
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
    
    @Bean
    @RouterOperations({
        @RouterOperation(
            path = ApiConstants.LOAN_PATH,
            beanClass = LoanHandler.class,
            beanMethod = "listenPOSTCreateLoan",
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
        return route(POST(ApiConstants.LOAN_PATH), handler::listenPOSTCreateLoan);
    }
}
