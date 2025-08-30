package co.com.pragma.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
      info = @Info(
            title = "CY Solicitud",
            version = "1.0.0",
            description = "Microservicio para manejo de solicitudes de prestamos",
            contact = @Contact(name = "Andres Camperos")
      )
)
public class SwaggerConfig {}