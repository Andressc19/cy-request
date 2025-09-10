package co.com.pragma.consumer;


import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.test.StepVerifier;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static reactor.test.StepVerifier.create;


class RestConsumerTest {
    
    private static RestConsumer restConsumer;
    private static MockWebServer mockBackEnd;
    
    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
        var webClient = WebClient.builder()
            .baseUrl(mockBackEnd.url("/").toString())
            .build();
        restConsumer = new RestConsumer(webClient);
    }
    
    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }
    
    @Test
    @DisplayName("Should return true when user exists")
    void shouldReturnTrueWhenUserExists() {
        mockBackEnd.enqueue(new MockResponse()
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .setResponseCode(HttpStatus.OK.value())
            .setBody("{\"exists\": true}"));
        
        var response = restConsumer.userExists("Bearer some_token");
        
        create(response)
            .expectNext(true)
            .verifyComplete();
    }
    
    @Test
    @DisplayName("Should return false when user does not exist")
    void shouldReturnFalseWhenUserDoesNotExist() {
        mockBackEnd.enqueue(new MockResponse()
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .setResponseCode(HttpStatus.OK.value())
            .setBody("{\"exists\": false}"));
        
        var response = restConsumer.userExists("Bearer some_token");
        
        create(response)
            .expectNext(false)
            .verifyComplete();
    }
    
    @Test
    @DisplayName("Should return false when doesn't have role privileges")
    void shouldReturnFalseWhenDoesNotHaveRolePrivileges() {
        MockResponse response = new MockResponse()
            .setResponseCode(401);
        
        mockBackEnd.enqueue(response);
        
        var result = restConsumer.userExists("invalid_token");
        
        create(result)
            .expectErrorSatisfies(throwable -> {
				assertInstanceOf(WebClientResponseException.class, throwable, "La excepción debe ser de tipo WebClientResponseException");
                WebClientResponseException ex = (WebClientResponseException) throwable;
                assertTrue(ex.getStatusCode().is4xxClientError(), "El código de estado debe ser de cliente (4xx)");
            })
            .verify();
    }
    
    
}