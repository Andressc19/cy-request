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
import reactor.test.StepVerifier;
import java.io.IOException;


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
        
        var response = restConsumer.userExists("test@email.com", "123456");
        
        StepVerifier.create(response)
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
        
        var response = restConsumer.userExists("notfound@email.com", "0000");
        
        StepVerifier.create(response)
            .expectNext(false)
            .verifyComplete();
    }
}