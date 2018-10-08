package pl.altkom.asc.lab.micronaut.poc.policy;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.Health;

@Client(id = "/policy-service", path = "/hello")
public interface HelloTestClient {

    @Get
    HttpStatus index();

    @Get("/version")
    Health version();
}