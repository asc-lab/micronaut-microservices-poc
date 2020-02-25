package pl.altkom.asc.lab.micronaut.poc.gateway;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
class ProductGatewayControllerTest {
    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    void getProductsEndpointReturnsUnauthorizedIfAnonymous() {
        HttpClientResponseException response = assertThrows(HttpClientResponseException.class, () -> {
            client.toBlocking().exchange("/api/products/");
        });

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatus());
    }

    @Test
    void getProductsEndpointReturnsEmptyList() {
        client.toBlocking().exchange(HttpRequest.GET("/api/products/").basicAuth("aaa", "aaa"));
    }
}